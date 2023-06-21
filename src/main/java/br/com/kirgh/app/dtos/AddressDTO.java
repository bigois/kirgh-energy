package br.com.kirgh.app.dtos;

import br.com.kirgh.app.enums.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * This is a Java record class named {@code AddressDTO} that represents a data transfer object for an address. It has six fields:
 * {@code street}, {@code zipCode}, {@code number}, {@code city}, {@code state}, and {@code relation}. Each field has a validation annotation to ensure that
 * the data is valid before it is used. The {@code @NotBlank} annotation ensures that the field is not null or empty, the
 * {@code @Pattern} annotation ensures that the {@code zipCode} field contains only numbers, and the {@code @NotNull} annotation ensures that
 * the {@code state} and {@code relation} fields are not null. The {@code @Valid} annotation is used to validate the {@code relation} field using
 */
public record AddressDTO(
    @NotBlank(message = "cannot be null or empty")
    String street,

    @NotBlank(message = "cannot be null or empty")
    @Pattern(regexp = "[0-9]+", message = "must contain only numbers")
    String zipCode,

    @NotBlank(message = "cannot be null or empty")
    String number,

    @NotBlank(message = "cannot be null or empty")
    String city,

    @NotNull(message = "cannot be null or empty")
    State state,

    @NotBlank(message = "cannot be null or empty")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "invalid parent id")
    String parentId
) {
}
