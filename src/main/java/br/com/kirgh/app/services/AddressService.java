package br.com.kirgh.app.services;

import br.com.kirgh.app.dtos.AddressDTO;
import br.com.kirgh.app.entities.Address;
import br.com.kirgh.app.entities.AddressRelation;
import br.com.kirgh.app.mappers.AddressMapper;
import br.com.kirgh.app.repositories.AddressRelationRepository;
import br.com.kirgh.app.repositories.AddressRepository;
import br.com.kirgh.app.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * The AddressService class creates and saves a new address to the database, along with its relation to a parent user, and
 * returns a response with the new address's ID and a success message.
 */
@Service
@SuppressWarnings("unused")
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRelationRepository addressRelationRepository;

    /**
     * The function creates a new address and saves it to the database, along with its relation to a parent user, and
     * returns a response with the new address's ID and a success message.
     *
     * @param addressDTO An object that contains the details of the address to be created, such as zip code, number, and
     *                   other relevant information.
     * @return A ResponseEntity object is being returned.
     */
    @Transactional
    public Address createAddress(AddressDTO addressDTO) {
        if (!userRepository.existsById(UUID.fromString(addressDTO.parentId()))) {
            throw new EntityNotFoundException("parent id not found");
        }

        if (addressRepository.existsToUserByUnique(UUID.fromString(addressDTO.parentId()), addressDTO.zipCode(), addressDTO.number())) {
            throw new IllegalArgumentException("address already exist to user");
        }

        Address address = addressRepository.save(AddressMapper.addressDTOToAddress(addressDTO));

        AddressRelation addressRelation = new AddressRelation();
        addressRelation.getAddressRelationPK().setAddress(address);
        addressRelation.getAddressRelationPK().setParent(userRepository.findById(UUID.fromString(addressDTO.parentId())).orElseThrow(() -> new EntityNotFoundException()));
        addressRelationRepository.save(addressRelation);

        return address;
    }
}
