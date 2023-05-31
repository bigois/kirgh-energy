package br.com.kirgh.app.mapper;

import br.com.kirgh.app.dtos.UserDTO;
import br.com.kirgh.app.entities.User;

public abstract class UserMapper {
    public static User userDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setCpf(userDTO.cpf());
        user.setEmail(userDTO.email());
        user.setName(userDTO.name());
        user.setGender(userDTO.gender());
        user.setBirthDate(userDTO.birthDate());

        return user;
    }
}
