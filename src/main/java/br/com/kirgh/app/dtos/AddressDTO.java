package br.com.kirgh.app.dtos;

import br.com.kirgh.app.enums.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * This is a record class named {@code AddressDTO} that represents a data transfer object for an address.
 */

/**
 * This is a Java record class named {@code AddressDTO} that represents a data transfer object for an
 * address. It has six fields: {@code street}, {@code zipCode}, {@code number}, {@code city}, {@code state},
 * and {@code parentId}. Eachfield has a validation annotation to ensure that the data is not null or empty and
 * meets certaincriteria (such as containing only numbers or matching a specific pattern). The record class is a
 * compact way to define a class that is primarily used to store data and does not have any behavior.
 * It automatically generates constructors, getters, and other methods based on the fields defined in
 * the class.
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
