package br.com.kirgh.app.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddressCompDTO {

    private AddressCompleteDTO addressData;
    private List<ApplianceCompleteDTO> appliances;

    public AddressCompDTO(AddressCompleteDTO address, List<ApplianceCompleteDTO> appliance) {
        addressData = address;
        appliances = appliance;
    }
}
