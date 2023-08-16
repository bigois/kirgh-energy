package br.com.kirgh.app.dtos;

import br.com.kirgh.app.constraints.CustomValidation;
import br.com.kirgh.app.enums.UserGender;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserUpdateDTO(
        @Size(min = 2, max = 150, message = "size must be between {min} and {max}")
        @Pattern(regexp = "[A-zÀ-ú\s]+", message = "must contain only letters")
        @Schema(description = "Name to identify an user", example = "Sophie Giovanna da Rocha")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String name,

        @CustomValidation(message = "not a valid representation of a past date [yyyy-MM-dd]")
        @Schema(description = "The date on which the person was born", example = "1986-12-01")
        String birthDate,

        @Pattern(regexp = "^[FM]$", message = "only basic biological human gender based on chromosome are allowed")
        @Schema(description = "Biological human genders based on chromosome", example = "F")
        String gender,

        // @NotBlank(message = "cannot be null or empty")
        @Size(min = 11, max = 11, message = "must have {min} characters")
        @CPF
        @Schema(description = "Brazilian individual taxpayer registry number (CPF)", example = "24705216377")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String cpf,

        @Email(message = "is invalid")
        @Size(min = 1, max = 80, message = "size must be between {min} and {max}")
        @Schema(description = "Field used to define the customer's email", example = "sophie.giovanna@sprintrental.com.br")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String email
) {
}
