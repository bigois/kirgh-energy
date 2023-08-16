package br.com.kirgh.app.services;

import br.com.kirgh.app.dtos.ApplianceInfoDTO;
import br.com.kirgh.app.dtos.ApplianceDTO;
import br.com.kirgh.app.dtos.ApplianceUpdateDTO;
import br.com.kirgh.app.entities.Appliance;
import br.com.kirgh.app.entities.ApplianceRelation;
import br.com.kirgh.app.mappers.ApplianceMapper;
import br.com.kirgh.app.projections.ApplianceProjection;
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
@SuppressWarnings({"unused", "SpringJavaAutowiredFieldsWarningInspection"})
public class ApplianceService {
    @Autowired
    private ApplianceRepository applianceRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ApplianceRelationRepository applianceRelationRepository;

    /**
     * This Java function creates a new Appliance object and saves it to the database with a
     * corresponding ApplianceRelation object.
     *
     * @param applianceDTO An object of type ApplianceDTO, which contains information about the
     *                     appliance to be created.
     * @return The method is returning an instance of the Appliance class.
     */
    @Transactional
    public Appliance createAppliance(ApplianceDTO applianceDTO) {
        if (!addressRepository.existsById(UUID.fromString(applianceDTO.addressId()))) {
            throw new EntityNotFoundException("address id not found");
        }

        Appliance appliance = applianceRepository.save(ApplianceMapper.applianceDTOToAppliance(applianceDTO));

        ApplianceRelation applianceRelation = new ApplianceRelation();
        applianceRelation.getApplianceRelationPK().setAppliance(appliance);
        applianceRelation.getApplianceRelationPK().setAddress(addressRepository.findById(UUID.fromString(applianceDTO.addressId())).orElseThrow(EntityNotFoundException::new));
        applianceRelationRepository.save(applianceRelation);

        return appliance;
    }

    @Transactional(readOnly = true)
    public Appliance getAllApplianceInfoById(UUID id) {
        Appliance appliance = applianceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("appliance not found"));
        return appliance;
    }

    @Transactional
    public Appliance updateApplianceInfoById(UUID id, ApplianceUpdateDTO applianceUpdateDTO) {
        if (applianceUpdateDTO.toString().replace("ApplianceUpdateDTO[", "").replace("]", "").split("null").length == 4) {
            throw new IllegalArgumentException("at least one attribute needs to be valid");
        }
        
        Appliance updateAppliance = applianceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("appliance not found"));
        applianceRepository.save(ApplianceMapper.applianceUpdateDTOToAppliance(applianceUpdateDTO, updateAppliance));
        return updateAppliance;
    }

    @Transactional
    public void deleteApplianceById(UUID id) {
        if (!applianceRepository.existsById(id)) {
            throw new EntityNotFoundException("appliance not found");
        }

        applianceRepository.deleteApplianceRelationById(id);
        applianceRepository.deleteApplianceById(id);
    }
}
