package br.com.kirgh.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

/**
 * The code snippet is defining a Java record class called {@code UserUpdateDTO}. A record is a class-like
 * construct introduced in Java 14 that provides a concise way to declare classes that are primarily
 * used to store data.
 */
@Schema(title = "UserUpdate", description = "User data update object")
public record UserUpdateDTO(
        @Size(min = 2, max = 150, message = "size must be between {min} and {max}")
        @Pattern(regexp = "[A-zÀ-ú]+", message = "must contain only letters")
        @Schema(description = "Name to identify an user", example = "Sophie Giovanna da Rocha")
        String name,

        @Past(message = "not a valid representation of a past date [yyyy-MM-dd]")
        @Schema(description = "The date on which the person was born", example = "1986-12-01")
        LocalDate birthDate,

        @Pattern(regexp = "^[FM]$", message = "only basic biological human gender based on chromosome are allowed")
        @Schema(description = "Biological human genders based on chromosome", example = "F")
        String gender,

        @Size(min = 11, max = 11, message = "must have {min} characters")
        @CPF
        @Schema(description = "Brazilian individual taxpayer registry number (CPF)", example = "24705216377")
        String cpf,

        @Email(message = "is invalid")
        @Size(min = 1, max = 80, message = "size must be between {min} and {max}")
        @Schema(description = "Field used to define the customer's email", example = "sophie.giovanna@sprintrental.com.br")
        String email
) {
}
