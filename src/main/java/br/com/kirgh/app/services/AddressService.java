package br.com.kirgh.app.services;

import br.com.kirgh.app.dtos.*;
import br.com.kirgh.app.entities.Address;
import br.com.kirgh.app.entities.AddressRelation;
import br.com.kirgh.app.entities.User;
import br.com.kirgh.app.mappers.AddressMapper;
import br.com.kirgh.app.mappers.ApplianceMapper;
import br.com.kirgh.app.projections.AddressProjection;
import br.com.kirgh.app.projections.ApplianceProjection;
import br.com.kirgh.app.repositories.AddressRelationRepository;
import br.com.kirgh.app.repositories.AddressRepository;
import br.com.kirgh.app.repositories.ApplianceRelationRepository;
import br.com.kirgh.app.repositories.ApplianceRepository;
import br.com.kirgh.app.repositories.UserRepository;
import br.com.kirgh.app.utils.Utils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private ApplianceRelationRepository applianceRelationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplianceService applianceService;

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
    public Page<Address> getAddresses(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Address> getFilteredAddresses(Map<String, String> filters, Pageable pageable) {
        Specification<Address> spec = (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            filters.forEach((key, value) -> {
                try {
                    Class<?> fieldType = root.get(key).getJavaType();
                    if (Enum.class.isAssignableFrom(fieldType)) {
                        Enum<?> enumValue = Enum.valueOf((Class<Enum>) fieldType, value);
                        predicates.add(builder.equal(root.get(key), enumValue));
                    } else {
                        predicates.add(builder.like(root.get(key), "%" + value + "%"));
                    }
                } catch (IllegalArgumentException | NullPointerException e) {
                    throw new IllegalArgumentException("field not found");
                }
            });
            return builder.and(predicates.toArray(new Predicate[0]));
        };

        return addressRepository.findAll(spec, pageable);
    }

    @Transactional(readOnly = true)
    public Address getAllAddressInfoById(UUID id) {
        return  addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("address not found"));
    }

    @Transactional(readOnly = true)
    public AddressCompleteInfoDTO getAllAppliancesBoundAddress(UUID id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("address not found"));
        List<ApplianceProjection> applianceProjection = applianceRepository.getAllAppliancesBoundAddress(id);

        List<ApplianceInfoDTO> applianceList = new ArrayList<>();

        AddressCompleteInfoDTO addressCompleteInfoDTO = new AddressCompleteInfoDTO();

        applianceProjection.stream().forEach(applianceItem -> applianceList.add(ApplianceMapper.applianceProjectionToApplianceInfoDTO(applianceItem)));

        addressCompleteInfoDTO.setAddressData(address);
        addressCompleteInfoDTO.setAppliances(applianceList);

        return addressCompleteInfoDTO;
    }

    @Transactional
    public Address updateAddressInfoById(UUID id, AddressUpdateDTO addressUpdateDTO) {
         if (addressUpdateDTO.toString().replace("AddressUpdateDTO[", "").replace("]", "").split("null").length == 5) {
            throw new IllegalArgumentException("at least one attribute needs to be valid");
        }
        
        Address updateAddress = addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("address not found"));
        addressRepository.save(AddressMapper.addressUpdateDTOToAddress(addressUpdateDTO, updateAddress));
        return updateAddress;
    }

    @Transactional
    public void deleteAddressById(UUID id){
        if(!addressRepository.existsById(id)){
            throw new EntityNotFoundException("address not found");
        }

        List<ApplianceProjection> applianceProjections =  applianceRepository.getAllAppliancesBoundAddress(id);
        applianceProjections.stream().forEach(applianceItem -> {
            applianceRelationRepository.deleteApplianceRelationByAddressId(id);
            applianceService.deleteApplianceById(Utils.convertBytesToUUID(applianceItem.getId()));
        });
        
        addressRelationRepository.deleteAddressesByAddressId(id);
        addressRepository.deleteAddressById(id);
    }
}
