package br.com.kirgh.app.dtos;

import br.com.kirgh.app.enums.Power;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * This is a Java record class named {@code ApplianceDTO} that represents a data transfer object for an appliance. It has five
 * fields: {@code name}, {@code brand}, {@code model}, {@code power}, and {@code addressId}. Each field has a validation annotation to ensure that it is
 * not null or empty. The {@code Power} field is an enum type representing the power source of the appliance. The record class is
 * immutable and automatically generates a constructor, getters, and {@code equals()} and {@code hashCode()} methods based on the
 * fields.
 */
@Schema(title = "Appliance", description = "Object that represents a data transfer object for an appliance")
public record ApplianceDTO(
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
        Power power,

        @Schema(description = "UUID from an existent address", example = "26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb")
        @NotBlank(message = "cannot be null or empty")
        @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "invalid address id")
        @Size(min = 36, max = 36, message = "size must be {max}")
        String addressId
) {
}
