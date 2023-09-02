package br.com.kirgh.app.dtos;

import br.com.kirgh.app.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
/**
 * The UserCompleteInfoDTO class is a data transfer object that contains information about a user,
 * their relations, and their addresses.
 */
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
