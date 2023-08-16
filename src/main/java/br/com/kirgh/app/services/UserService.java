package br.com.kirgh.app.services;

import br.com.kirgh.app.dtos.*;
import br.com.kirgh.app.entities.User;
import br.com.kirgh.app.entities.UserRelation;
import br.com.kirgh.app.mappers.UserMapper;
import br.com.kirgh.app.projections.AddressProjection;
import br.com.kirgh.app.projections.UserProjection;
import br.com.kirgh.app.repositories.AddressRepository;
import br.com.kirgh.app.repositories.ApplianceRepository;
import br.com.kirgh.app.repositories.UserRelationRepository;
import br.com.kirgh.app.repositories.UserRepository;
import br.com.kirgh.app.utils.Utils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The UserService class creates a new user and saves it to the database, along with a user relation if specified, and
 * returns a success message.
 */
@Service
@SuppressWarnings({"unused", "SpringJavaAutowiredFieldsWarningInspection"})
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ApplianceRepository applianceRepository;

    @Autowired
    private UserRelationRepository userRelationRepository;

    @Autowired
    private AddressService addressService;

    /**
     * This Java function creates a new user and saves it to the database, along with a user relation
     * if specified, while checking for existing users and owner IDs.
     *
     * @param userDTO A data transfer object (DTO) representing a user, containing fields such as name,
     *                email, CPF (Brazilian tax ID), and a relation object (if applicable).
     * @return The method is returning a User object.
     */
    @Transactional
    public User createUser(UserDTO userDTO) {
        if (userRepository.existsByCpf(userDTO.cpf()) || userRepository.existsByEmail(userDTO.email())) {
            throw new IllegalArgumentException("user already exists");
        }

        if (userDTO.relation() != null && !userRepository.existsById(UUID.fromString(userDTO.relation().ownerId()))) {
            throw new EntityNotFoundException("owner id not found");
        }

        User user = userRepository.save(UserMapper.userDTOToUser(userDTO));

        if (userDTO.relation() != null) {
            UserRelation userRelation = new UserRelation();
            userRelation.setRelationType(userDTO.relation().relationType());
            userRelation.getUserRelationPK().setChild(user);
            userRelation.getUserRelationPK().setOwner(userRepository.findById(UUID.fromString(userDTO.relation().ownerId())).orElseThrow(EntityNotFoundException::new));
            userRelationRepository.save(userRelation);
        }

        return user;
    }

    @Transactional(readOnly = true)
    public User getAllUserInfoById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user not found"));

        return user;
    }

    @Transactional(readOnly = true)
    public UserCompleteInfoDTO getAllAddressesBoundUser(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user not found"));
        List<AddressProjection> addressProjection = addressRepository.getAllAddressesBoundUser(id);

        List<AddressCompleteInfoDTO> addressList = new ArrayList<>();

        UserCompleteInfoDTO userCompleteInfoDTO = new UserCompleteInfoDTO();
        userCompleteInfoDTO.setUserData(user);

        for (AddressProjection addressItem : addressProjection) {
            addressList.add(addressService.getAllAppliancesBoundAddress(Utils.convertBytesToUUID(addressItem.getId())));
        }

        userCompleteInfoDTO.setAddresses(addressList);

        return userCompleteInfoDTO;
    }

    @Transactional
    public User updateUserInfoById(UUID id, UserUpdateDTO userUpdateDTO) {
        if (userUpdateDTO.toString().replace("UserUpdateDTO[", "").replace("]", "").split("null").length == 5) {
            throw new IllegalArgumentException("at least one attribute needs to be valid");
        }
        
        User updateUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("user not found"));
        userRepository.save(UserMapper.userUpdateDTOToUser(userUpdateDTO, updateUser));
        return updateUser;
    }

    @Transactional
    public void deleteUserById(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("user not found");
        }

        userRepository.deleteParentRelationById(id);
        userRepository.deleteAddressRelationById(id);
        userRepository.deleteUserById(id);
    }
}
