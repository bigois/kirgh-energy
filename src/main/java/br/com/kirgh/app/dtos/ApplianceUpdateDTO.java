package br.com.kirgh.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ApplianceUpdateDTO(
        @Schema(description = "Name for identifying an appliance in an address", example = "Ar Condicionado")
        @Size(min = 1, max = 150, message = "size must be between {min} and {max}")
        String name,

        @Schema(description = "Brand name that helps people recognize and identify an appliance manufacturer", example = "Samsung")
        @Size(min = 1, max = 150, message = "size must be between {min} and {max}")
        String brand,

        @Schema(description = "A combination of numbers and letters to identify an appliance", example = "AR12BVHZCWK")
        @Size(min = 1, max = 150, message = "size must be between {min} and {max}")
        String model,

        @Schema(description = "Electric tension or potential difference unit from an appliance", example = "V110")
        @Pattern(regexp = "^[A-Z][0-9]{0,3}$", message = "incorrect format power, must be V110/V220")
        String power
) {
}
