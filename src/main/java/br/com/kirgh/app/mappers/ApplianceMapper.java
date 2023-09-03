package br.com.kirgh.app.mappers;

import br.com.kirgh.app.dtos.ApplianceDTO;
import br.com.kirgh.app.dtos.ApplianceInfoDTO;
import br.com.kirgh.app.dtos.ApplianceUpdateDTO;
import br.com.kirgh.app.entities.Appliance;
import br.com.kirgh.app.enums.Power;
import br.com.kirgh.app.projections.ApplianceProjection;
import br.com.kirgh.app.utils.Utils;

/**
 * The {@code ApplianceMapper} class converts an {@code ApplianceDTO} object to an {@code Appliance} object by setting the corresponding
 * attributes.
 */
public abstract class ApplianceMapper {
    /**
     * The function converts an {@code ApplianceDTO} object to an {@code Appliance} object by setting the corresponding attributes.
     *
     * @param applianceDTO An object of type {@code ApplianceDTO}, which contains information about an appliance such as its name,
     *                     brand, model, and power consumption.
     * @return An instance of the {@code Appliance} class is being returned.
     */
    public static Appliance applianceDTOToAppliance(ApplianceDTO applianceDTO) {
        Appliance appliance = new Appliance();

        appliance.setName(applianceDTO.name());
        appliance.setBrand(applianceDTO.brand());
        appliance.setModel(applianceDTO.model());
        appliance.setPower(applianceDTO.power());

        return appliance;
    }

    /**
     * The function takes an {@code ApplianceUpdateDTO} object and updates the corresponding Appliance object
     * with the values from the DTO.
     *
     * @param applianceUpdateDTO An object of type {@code ApplianceUpdateDTO}, which contains the updated
     *                           information for an appliance.
     * @param updateAppliance    The {@code updateAppliance} parameter is an instance of the {@code Appliance} class
     *                           that needs to be updated with the values from the {@code ApplianceUpdateDTO} parameter.
     * @return The method is returning the updated {@code Appliance} object.
     */
    public static Appliance applianceUpdateDTOToAppliance(ApplianceUpdateDTO applianceUpdateDTO, Appliance updateAppliance) {
        if (applianceUpdateDTO.name() != null) {
            updateAppliance.setName(applianceUpdateDTO.name());
        }

        if (applianceUpdateDTO.brand() != null) {
            updateAppliance.setBrand(applianceUpdateDTO.brand());
        }

        if (applianceUpdateDTO.model() != null) {
            updateAppliance.setModel(applianceUpdateDTO.model());
        }

        if (applianceUpdateDTO.power() != null) {
            updateAppliance.setPower(Power.valueOf(applianceUpdateDTO.power()));
        }

        return updateAppliance;
    }

    /**
     * The function {@code applianceProjectionToApplianceInfoDTO} converts an {@code ApplianceProjection} object
     * into an {@code ApplianceInfoDTO} object.
     *
     * @param applianceProjection The parameter {@code applianceProjection} is of type {@code ApplianceProjection}.
     *                            It represents an object that contains information about an appliance, such as its id, name,
     *                            brand, model, and power.
     * @return The method is returning an instance of the {@code ApplianceInfoDTO} class.
     */
    public static ApplianceInfoDTO applianceProjectionToApplianceInfoDTO(ApplianceProjection applianceProjection) {
        return new ApplianceInfoDTO(Utils.convertBytesToUUID(applianceProjection.getId()),
                applianceProjection.getName(), applianceProjection.getBrand(), applianceProjection.getModel(), applianceProjection.getPower());
    }
}
