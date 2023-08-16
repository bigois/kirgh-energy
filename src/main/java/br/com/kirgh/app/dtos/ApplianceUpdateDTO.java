package br.com.kirgh.app.dtos;

import br.com.kirgh.app.enums.Power;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ApplianceUpdateDTO(
        @Schema(description = "Name for identifying an appliance in an address", example = "Ar Condicionado")
        @NotBlank(message = "cannot be null or empty")
        @Size(min = 1, max = 150, message = "size must be between {min} and {max}")
        String name,

        @Schema(description = "Brand name that helps people recognize and identify an appliance manufacturer", example = "Samsung")
        @NotBlank(message = "cannot be null or empty")
        @Size(min = 1, max = 150, message = "size must be between {min} and {max}")
        String brand,

        @Schema(description = "A combination of numbers and letters to identify an appliance", example = "AR12BVHZCWK")
        @NotBlank(message = "cannot be null or empty")
        @Size(min = 1, max = 150, message = "size must be between {min} and {max}")
        String model,

        @Schema(description = "Electric tension or potential difference unit from an appliance", example = "V110")
        @NotNull(message = "cannot be null or empty")
        Power power
) {
}