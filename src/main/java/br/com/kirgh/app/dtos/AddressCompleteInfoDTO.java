package br.com.kirgh.app.dtos;

import br.com.kirgh.app.entities.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * The {@code AddressCompleteInfoDTO} class represents complete information about an address, including the
 * address data and a list of appliance information.
 */
@Getter
@Setter
@NoArgsConstructor
public class AddressCompleteInfoDTO {
    private Address addressData;
    private List<ApplianceInfoDTO> appliances;
}
