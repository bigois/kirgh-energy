package br.com.kirgh.app.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import br.com.kirgh.app.entities.Address;

@Getter
@Setter
@NoArgsConstructor
public class AddressCompleteInfoDTO {
    private Address addressData;
    private List<ApplianceInfoDTO> appliances;

    public AddressCompleteInfoDTO(Address address, List<ApplianceInfoDTO> appliance) {
        addressData = address;
        appliances = appliance;
    }
}
