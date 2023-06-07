package br.com.kirgh.app.services;

import br.com.kirgh.app.dtos.AddressDTO;
import br.com.kirgh.app.entities.Address;
import br.com.kirgh.app.entities.AddressRelation;
import br.com.kirgh.app.mapper.AddressMapper;
import br.com.kirgh.app.repositories.AddressRelationRepository;
import br.com.kirgh.app.repositories.AddressRepository;
import br.com.kirgh.app.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("unused")
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRelationRepository addressRelationRepository;

    public ResponseEntity<?> createAddress(AddressDTO addressDTO) {
        JSONObject response = new JSONObject();

        if (!userRepository.existsById(addressDTO.relation().parentId())) {
            throw new EntityNotFoundException("parent id not found");
        }

        if (addressRepository.existsToUserByUnique(addressDTO.relation().parentId(), addressDTO.zipCode(), addressDTO.number())) {
            throw new IllegalArgumentException("address already exist to user");
        }

        Address address = addressRepository.save(AddressMapper.addressTOToAddress(addressDTO));

        AddressRelation addressRelation = new AddressRelation();
        addressRelation.getAddressRelationPK().setAddress(address);
        addressRelation.getAddressRelationPK().setParent(userRepository.findById(addressDTO.relation().parentId()).orElse(null));

        addressRelationRepository.save(addressRelation);

        response.put("message", "address successfully registered");
        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }
}
