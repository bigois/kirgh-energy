package br.com.kirgh.app.dtos;

import br.com.kirgh.app.enums.State;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
    
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

    @Valid
    @NotNull(message = "cannot be null or empty")
    AddressRelationDTO relation
) {
    
}

