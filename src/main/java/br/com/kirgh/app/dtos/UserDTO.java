package br.com.kirgh.app.dtos;

import br.com.kirgh.app.enums.UserGender;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

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
