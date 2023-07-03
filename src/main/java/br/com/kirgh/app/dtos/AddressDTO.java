package br.com.kirgh.app.dtos;

import br.com.kirgh.app.enums.State;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * This is a Java record class named {@code AddressDTO} that represents a data transfer object for an
 * address. It has six fields: {@code street}, {@code zipCode}, {@code number}, {@code city}, {@code state},
 * and {@code parentId}. Each field has a validation annotation to ensure that the data is not null or empty and
 * meets certain criteria (such as containing only numbers or matching a specific pattern). The record class is a
 * compact way to define a class that is primarily used to store data and does not have any behavior.
 * It automatically generates constructors, getters, and other methods based on the fields defined in
 * the class.
 */
@Schema(title = "Address", description = "Object that represents a data transfer object for an address")
public record AddressDTO(
        @Schema(description = "Street name that identifies where you physically live", example = "Rua Alicante")
        @NotBlank(message = "cannot be null or empty")
        @Size(min = 1, max = 150, message = "size must be between {min} and {max}")
        String street,

        @Schema(description = "A group of 8 numbers that are added to a postal address to assist the sorting of mail", example = "03654010")
        @NotBlank(message = "cannot be null or empty")
        @Pattern(regexp = "[0-9]+", message = "must contain only numbers")
        @Size(min = 8, max = 8, message = "size must be {max}")
        String zipCode,

        @Schema(description = "The numerical addresses assigned to homes, businesses, and other properties", example = "966")
        @NotBlank(message = "cannot be null or empty")
        @Size(min = 1, max = 10, message = "size must be between {min} and {max}")
        String number,

        @Schema(description = "Relatively permanent and highly organized centre of population", example = "São Paulo")
        @NotBlank(message = "cannot be null or empty")
        @Size(min = 1, max = 100, message = "size must be between {min} and {max}")
        String city,

        @Schema(description = "Territory considered as an organized political community under brazilian government", example = "SP")
        @NotNull(message = "cannot be null or empty")
        State state,

        @Schema(description = "UUID from an existent user", example = "7049f9a7-05ec-4c4f-9bbf-44648d87e7a8")
        @NotBlank(message = "cannot be null or empty")
        @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "invalid parent id")
        @Size(min = 36, max = 36, message = "size must be {max}")
        String parentId
) {
}
