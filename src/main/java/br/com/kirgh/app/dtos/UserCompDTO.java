package br.com.kirgh.app.dtos;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCompDTO {
    
    private UserCompleteDTO userData;
    private List<AddressCompleteDTO> addresses;

    public UserCompDTO(UserCompleteDTO userDTO, List<AddressCompleteDTO> address){
        userData = userDTO;
        addresses = address;
    }

}
