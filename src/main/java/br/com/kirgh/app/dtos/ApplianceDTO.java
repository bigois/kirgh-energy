package br.com.kirgh.app.dtos;

import br.com.kirgh.app.enums.Power;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * This is a Java record class named {@code ApplianceDTO} that represents a data transfer object for an appliance. It has five
 * fields: {@code name}, {@code brand}, {@code model}, {@code power}, and {@code addressId}. Each field has a validation annotation to ensure that it is
 * not null or empty. The {@code Power} field is an enum type representing the power source of the appliance. The record class is
 * immutable and automatically generates a constructor, getters, and {@code equals()} and {@code hashCode()} methods based on the
 * fields.
 */
public record ApplianceDTO(
    @NotBlank(message = "cannot be null or empty")
    String name,

    @NotBlank(message = "cannot be null or empty")
    String brand,

    @NotBlank(message = "cannot be null or empty")
    String model,

    @NotNull(message = "cannot be null or empty")
    Power power,

    @NotNull(message = "cannot be null or empty")
    Long addressId
) {
}
