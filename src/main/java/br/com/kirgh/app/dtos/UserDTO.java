package br.com.kirgh.app.dtos;

import br.com.kirgh.app.enums.UserGender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

/**
 * This is a Java record class called {@code UserDTO} that represents a data transfer object for a user. It has six fields:
 * {@code name}, {@code birthDate}, {@code gender}, {@code cpf}, {@code email}, and {@code relation}. Each field has validation annotations that specify
 * constraints on the data that can be stored in the field. For example, the {@code name} field must not be blank, must be
 * between 2 and 150 characters in length, and must contain only letters. The {@code cpf} field must not be blank, must be
 * exactly 11 characters in length, and must be a valid Brazilian CPF number. The {@code relation} field is annotated with
 * {@code @Valid}, which means that any nested objects of type {@code UserRelationDTO} will also be validated. This record class is
 * used to transfer user data between different parts of an application or between different applications.
 */
public record UserDTO(
    @NotBlank(message = "cannot be null or empty")
    @Size(min = 2, max = 150, message = "size must be between {min} and {max}")
    @Pattern(regexp = "[A-zÀ-ú\s]+", message = "must contain only letters")
    String name,

    @NotNull(message = "cannot be null or empty")
    @Past(message = "must be before the current date")
    Date birthDate,

    @NotNull(message = "cannot be null or empty")
    UserGender gender,

    @NotBlank(message = "cannot be null or empty")
    @Size(min = 11, max = 11, message = "must have {min} characters")
    @CPF
    String cpf,

    @NotBlank(message = "cannot be null or empty")
    @Email(message = "is invalid")
    String email,

    @Valid
    UserRelationDTO relation
) {
}
