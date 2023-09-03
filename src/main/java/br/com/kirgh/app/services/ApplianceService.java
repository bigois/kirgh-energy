package br.com.kirgh.app.services;

import br.com.kirgh.app.dtos.ApplianceDTO;
import br.com.kirgh.app.dtos.ApplianceUpdateDTO;
import br.com.kirgh.app.entities.Appliance;
import br.com.kirgh.app.entities.ApplianceRelation;
import br.com.kirgh.app.mappers.ApplianceMapper;
import br.com.kirgh.app.repositories.AddressRepository;
import br.com.kirgh.app.repositories.ApplianceRelationRepository;
import br.com.kirgh.app.repositories.ApplianceRepository;
import br.com.kirgh.app.utils.Utils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
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
     * This {@code Java} function creates a new {@code Appliance} object and saves it to the database with a
     * corresponding {@code ApplianceRelation} object.
     *
     * @param applianceDTO An object of type {@code ApplianceDTO}, which contains information about the
     *                     appliance to be created.
     * @return The method is returning an instance of the {@code Appliance} class.
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

    /**
     * This function retrieves a page of filtered appliances based on the provided filters and pageable
     * information.
     *
     * @param filters  A map containing the filters to be applied to the query. The keys represent the
     *                 field names and the values represent the filter values.
     * @param pageable The {@code pageable} parameter is used to specify the pagination settings for the
     *                 query. It includes information such as the page number, page size, and sorting criteria. It
     *                 allows you to retrieve a specific page of results from the query.
     * @return The method is returning a {@code Page} object containing a list of {@code Appliance} objects that match
     * the specified filters and are paginated according to the provided {@code Pageable} object.
     */
    @Transactional(readOnly = true)
    public Page<Appliance> getFilteredAppliances(Map<String, String> filters, Pageable pageable) {
        Utils.validateFilters(filters, Appliance.class);
        Specification spec = Utils.buildSpecification(filters);

        return applianceRepository.findAll(spec, pageable);
    }

    /**
     * The function retrieves all information about an appliance by its ID from the appliance
     * repository, and throws an exception if the appliance is not found.
     *
     * @param id The id parameter is of type UUID and represents the unique identifier of the appliance
     *           for which the information is being retrieved.
     * @return The method is returning an instance of the {@code Appliance} class.
     */
    @Transactional(readOnly = true)
    public Appliance getAllApplianceInfoById(UUID id) {
        return applianceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("appliance not found"));
    }

    /**
     * The function updates the information of an appliance based on the provided ID and returns the
     * updated appliance.
     *
     * @param id                 The id parameter is of type UUID and represents the unique identifier of the appliance
     *                           that needs to be updated.
     * @param applianceUpdateDTO The {@code applianceUpdateDTO} parameter is an object of type
     *                           {@code ApplianceUpdateDTO}. It is used to update the information of an appliance.
     * @return The method is returning an instance of the {@code Appliance} class.
     */
    @Transactional
    public Appliance updateApplianceInfoById(UUID id, ApplianceUpdateDTO applianceUpdateDTO) {
        if (applianceUpdateDTO.toString().replace("ApplianceUpdateDTO[", "").replace("]", "").split("null").length == 4) {
            throw new IllegalArgumentException("at least one attribute needs to be valid");
        }

        Appliance updateAppliance = applianceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("appliance not found"));
        applianceRepository.save(ApplianceMapper.applianceUpdateDTOToAppliance(applianceUpdateDTO, updateAppliance));
        return updateAppliance;
    }

    /**
     * This function deletes an appliance by its ID, throwing an exception if the appliance is not
     * found.
     *
     * @param id The id parameter is of type UUID and represents the unique identifier of the appliance
     *           that needs to be deleted.
     */
    @Transactional
    public void deleteApplianceById(UUID id) {
        if (!applianceRepository.existsById(id)) {
            throw new EntityNotFoundException("appliance not found");
        }

        applianceRelationRepository.deleteApplianceRelationById(id);
        applianceRepository.deleteApplianceById(id);
    }
}
