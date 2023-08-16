package br.com.kirgh.app.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import br.com.kirgh.app.entities.User;

@Getter
@Setter
@NoArgsConstructor
public class UserCompleteInfoDTO {
    private User userData;
    private List<AddressCompleteInfoDTO> addresses;

    public UserCompleteInfoDTO(User user, List<AddressCompleteInfoDTO> address) {
        userData = user;
        addresses = address;
    }
}
