package br.com.kirgh.app.mappers;

import br.com.kirgh.app.dtos.ApplianceDTO;
import br.com.kirgh.app.entities.Appliance;

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
}
