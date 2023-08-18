package br.com.kirgh.app.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import br.com.kirgh.app.entities.User;
import br.com.kirgh.app.entities.UserRelation;

@Getter
@Setter
@NoArgsConstructor
public class UserCompleteInfoDTO {
    private User userData;
    private List<UserRelationInfoDTO> userRelation;
    private List<AddressCompleteInfoDTO> addresses;

    public UserCompleteInfoDTO(User user, List<UserRelationInfoDTO> relations, List<AddressCompleteInfoDTO> address) {
        userData = user;
        userRelation = relations;
        addresses = address;
    }
}