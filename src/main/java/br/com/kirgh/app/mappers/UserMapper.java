package br.com.kirgh.app.mappers;

import br.com.kirgh.app.dtos.UserDTO;
import br.com.kirgh.app.dtos.UserUpdateDTO;
import br.com.kirgh.app.entities.User;
import br.com.kirgh.app.enums.UserGender;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * The {@code UserMapper} class provides a static method to convert a {@code UserDTO} object to a {@code User} object in Java.
 */
public abstract class UserMapper {
    /**
     * The function converts a UserDTO object to a User object in Java.
     *
     * @param userDTO userDTO is an object of type UserDTO, which is a data transfer object that contains information about
     *                a user.
     * @return The method {@code userDTOToUser} is returning an instance of the {@code User} class, which is created based on the
     * information provided in the {@code UserDTO} object passed as a parameter.
     */
    public static User userDTOToUser(UserDTO userDTO) {
        User user = new User();

        user.setCpf(userDTO.cpf());
        user.setEmail(userDTO.email());
        user.setName(userDTO.name());
        user.setGender(userDTO.gender());
        user.setBirthDate(userDTO.birthDate());

        return user;
    }

    @SneakyThrows
    public static User userUpdateDTOToUser(UserUpdateDTO userUpdateDTO, User updateUser) {
        if (userUpdateDTO.cpf() != null) {
            updateUser.setCpf(userUpdateDTO.cpf());
        }

        if (userUpdateDTO.email() != null) {
            updateUser.setEmail(userUpdateDTO.email());
        }

        if (userUpdateDTO.name() != null) {
            updateUser.setName(userUpdateDTO.name());
        }

        if (userUpdateDTO.gender() != null) {
            updateUser.setGender(UserGender.valueOf(userUpdateDTO.gender()));
        }

        if (userUpdateDTO.birthDate() != null) {
            updateUser.setBirthDate(new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).parse(userUpdateDTO.birthDate()));
        }

        return updateUser;
    }
}
