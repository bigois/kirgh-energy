package br.com.kirgh.app.dtos;

import java.util.List;

import br.com.kirgh.app.projections.ApplianceProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressCompDTO {
    
        private AddressCompleteDTO addressData;
        private List<ApplianceProjection> appliances;

        public AddressCompDTO(AddressCompleteDTO address, List<ApplianceProjection> appliance){
            addressData = address;
            appliances = appliance;
        } 
}
