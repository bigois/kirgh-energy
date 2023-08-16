package br.com.kirgh.app.services;

import br.com.kirgh.app.dtos.*;
import br.com.kirgh.app.entities.Address;
import br.com.kirgh.app.entities.AddressRelation;
import br.com.kirgh.app.mappers.AddressMapper;
import br.com.kirgh.app.mappers.ApplianceMapper;
import br.com.kirgh.app.projections.AddressProjection;
import br.com.kirgh.app.projections.ApplianceProjection;
import br.com.kirgh.app.repositories.AddressRelationRepository;
import br.com.kirgh.app.repositories.AddressRepository;
import br.com.kirgh.app.repositories.ApplianceRepository;
import br.com.kirgh.app.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The AddressService class creates and saves a new address to the database, along with its relation to a parent user, and
 * returns a response with the new address's ID and a success message.
 */
@Service
@SuppressWarnings({"unused", "SpringJavaAutowiredFieldsWarningInspection"})
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ApplianceRepository applianceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRelationRepository addressRelationRepository;

    /**
     * This function creates a new address and saves it to the database, while also checking if
     * the parent user exists and if the address already exists for that user.
     *
     * @param addressDTO An object of type AddressDTO that contains the information needed to create a
     *                   new address.
     * @return The method is returning an instance of the Address class.
     */
    @Transactional
    public Address createAddress(AddressDTO addressDTO) {
        if (!userRepository.existsById(UUID.fromString(addressDTO.parentId()))) {
            throw new EntityNotFoundException("parent id not found");
        }

        if (addressRepository.existsToUserByUnique(UUID.fromString(addressDTO.parentId()), addressDTO.zipCode(), addressDTO.number())) {
            throw new IllegalArgumentException("address already exists to user");
        }

        Address address = addressRepository.save(AddressMapper.addressDTOToAddress(addressDTO));

        AddressRelation addressRelation = new AddressRelation();
        addressRelation.getAddressRelationPK().setAddress(address);
        addressRelation.getAddressRelationPK().setParent(userRepository.findById(UUID.fromString(addressDTO.parentId())).orElseThrow(EntityNotFoundException::new));
        addressRelationRepository.save(addressRelation);

        return address;
    }

    @Transactional(readOnly = true)
    public AddressCompleteDTO getAllAddressInfoById(UUID id) {
        if (!addressRepository.existsById(id)) {
            throw new EntityNotFoundException("address not found");
        }
        AddressProjection addressProjection = addressRepository.getAllAddressInfoById(id);
        return AddressMapper.addressCompleteProjectionToAddressCompleteDTO(addressProjection);
    }

    @Transactional(readOnly = true)
    public AddressCompDTO getAllAppliancesBoundAddress(UUID id) {
        if (!addressRepository.existsById(id)) {
            throw new EntityNotFoundException("address not found");
        }

        AddressProjection addressProjection = addressRepository.getAllAddressInfoById(id);
        List<ApplianceProjection> applianceProjection = applianceRepository.getAllAppliancesBoundAddress(id);

        List<ApplianceCompleteDTO> applianceList = new ArrayList<>();

        AddressCompDTO addressCompDTO = new AddressCompDTO();

        for (ApplianceProjection applianceItem : applianceProjection) {
            applianceList.add(ApplianceMapper.applianceCompleteProjectionToApplianceCompleteDTO(applianceItem));
        }

        addressCompDTO.setAddressData(AddressMapper.addressCompleteProjectionToAddressCompleteDTO(addressProjection));
        addressCompDTO.setAppliances(applianceList);

        return addressCompDTO;
    }

    @Transactional
    public Address updateAddressInfoById(UUID id, AddressUpdateDTO addressUpdateDTO) {
        Address updateAddress = addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("address not found"));
        addressRepository.save(AddressMapper.addressUpdateDTOToAddress(addressUpdateDTO, updateAddress));
        return updateAddress;
    }

    @Transactional
    public void deleteAddressById(UUID id) {
        if (!addressRepository.existsById(id)) {
            throw new EntityNotFoundException("address not found");
        }


        addressRepository.deleteAddressRelationById(id);
        addressRepository.deleteApplianceRelationById(id);
        addressRepository.deleteAddressById(id);
    }
}
