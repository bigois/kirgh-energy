package br.com.kirgh.app.dtos;

import br.com.kirgh.app.enums.Power;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
