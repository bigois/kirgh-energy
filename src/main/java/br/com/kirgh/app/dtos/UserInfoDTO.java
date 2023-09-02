package br.com.kirgh.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;

// The code snippet is defining a Java record class called `UserInfoDTO` which represents a data
// transfer object for user data.
@Schema(title = "UserComplete", description = "Object that represents a data transfer object for all user data")
public record UserInfoDTO(
        @NotBlank(message = "cannot be null or empty")
        @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "invalid user id")
        @Size(min = 36, max = 36, message = "size must be {max}")
        @Schema(description = "UUID from an existent user", example = "67459848-3af5-4c99-9276-543c331adcc1")
        UUID id,

        @NotBlank(message = "cannot be null or empty")
        @Size(min = 2, max = 150, message = "size must be between {min} and {max}")
        @Pattern(regexp = "[A-zÀ-ú ]+", message = "must contain only letters")
        @Schema(description = "Name to identify an user", example = "Sophie Giovanna da Rocha")
        String name,

        @NotNull(message = "cannot be null or empty")
        @Past(message = "must be before the current date")
        @Schema(description = "The date on which the person was born", example = "1986-12-01")
        LocalDate birthDate,

        @NotNull(message = "cannot be null or empty")
        @Schema(description = "Biological human genders based on chromosome", example = "F")
        String gender,

        @NotBlank(message = "cannot be null or empty")
        @Size(min = 11, max = 11, message = "must have {min} characters")
        @CPF
        @Schema(description = "Brazilian individual taxpayer registry number (CPF)", example = "24705216377")
        String cpf,

        @NotBlank(message = "cannot be null or empty")
        @Email(message = "is invalid")
        @Size(min = 1, max = 80, message = "size must be between {min} and {max}")
        @Schema(description = "Field used to define the customer's email", example = "sophie.giovanna@sprintrental.com.br")
        String email
) {
}
