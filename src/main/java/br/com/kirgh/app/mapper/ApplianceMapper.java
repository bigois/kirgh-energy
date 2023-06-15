package br.com.kirgh.app.mapper;

import br.com.kirgh.app.dtos.ApplianceDTO;
import br.com.kirgh.app.entities.Appliance;

public abstract class ApplianceMapper {
    public static Appliance applianceDTOToAppliance(ApplianceDTO applianceDTO) {
        Appliance appliance = new Appliance();
        appliance.setName(applianceDTO.name());
        appliance.setBrand(applianceDTO.brand());
        appliance.setModel(applianceDTO.model());
        appliance.setPower(applianceDTO.power());

        return appliance;
    }
    
}
