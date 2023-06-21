package br.com.kirgh.app.services;

import br.com.kirgh.app.dtos.ApplianceDTO;
import br.com.kirgh.app.entities.Appliance;
import br.com.kirgh.app.entities.ApplianceRelation;
import br.com.kirgh.app.mappers.ApplianceMapper;
import br.com.kirgh.app.repositories.AddressRepository;
import br.com.kirgh.app.repositories.ApplianceRelationRepository;
import br.com.kirgh.app.repositories.ApplianceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * This is a Java class that creates an appliance and saves it to a repository, along with its relation to an address.
 */
@Service
@SuppressWarnings("unused")
public class ApplianceService {
    @Autowired
    private ApplianceRepository applianceRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ApplianceRelationRepository applianceRelationRepository;

    /**
     * The function creates a new appliance and saves it to the database with a relation to an address, returning a
     * response with the new appliance's ID and a success message.
     *
     * @param applianceDTO An object of type ApplianceDTO which contains the information needed to create a new Appliance
     *                     entity.
     * @return A ResponseEntity object is being returned, which contains a JSON response with the resource ID and a success
     * message.
     */
    @Transactional
    public Appliance createAppliance(ApplianceDTO applianceDTO) {
        if (!addressRepository.existsById(UUID.fromString(applianceDTO.addressId()))) {
            throw new EntityNotFoundException("address id not found");
        }

        Appliance appliance = applianceRepository.save(ApplianceMapper.applianceDTOToAppliance(applianceDTO));

        ApplianceRelation applianceRelation = new ApplianceRelation();
        applianceRelation.getApplianceRelationPK().setAppliance(appliance);
        applianceRelation.getApplianceRelationPK().setAddress(addressRepository.findById(UUID.fromString(applianceDTO.addressId())).orElseThrow(() -> new EntityNotFoundException()));
        applianceRelationRepository.save(applianceRelation);

        return appliance;
    }
}
