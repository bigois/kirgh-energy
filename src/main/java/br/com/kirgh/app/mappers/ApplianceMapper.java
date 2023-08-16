package br.com.kirgh.app.mappers;

import br.com.kirgh.app.dtos.ApplianceInfoDTO;
import br.com.kirgh.app.dtos.ApplianceDTO;
import br.com.kirgh.app.dtos.ApplianceUpdateDTO;
import br.com.kirgh.app.entities.Appliance;
import br.com.kirgh.app.enums.Power;
import br.com.kirgh.app.projections.ApplianceProjection;
import br.com.kirgh.app.utils.Utils;

/**
 * The ApplianceMapper class converts an ApplianceDTO object to an Appliance object by setting the corresponding
 * attributes.
 */
public abstract class ApplianceMapper {
    /**
     * The function converts an ApplianceDTO object to an Appliance object by setting the corresponding attributes.
     *
     * @param applianceDTO An object of type ApplianceDTO, which contains information about an appliance such as its name,
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

    public static ApplianceInfoDTO applianceProjectionToApplianceInfoDTO(ApplianceProjection applianceProjection) {
        return new ApplianceInfoDTO(Utils.convertBytesToUUID(applianceProjection.getId()),
        applianceProjection.getName(), applianceProjection.getBrand(), applianceProjection.getModel(), applianceProjection.getPower());
    }
}
