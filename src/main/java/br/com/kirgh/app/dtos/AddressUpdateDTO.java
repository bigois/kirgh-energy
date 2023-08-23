package br.com.kirgh.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressUpdateDTO(
        @Schema(description = "Street name that identifies where you physically live", example = "Rua Alicante")
        @Size(min = 1, max = 150, message = "size must be between {min} and {max}")
        String street,

        @Schema(description = "A group of 8 numbers that are added to a postal address to assist the sorting of mail", example = "03654010")
        @Pattern(regexp = "[0-9]+", message = "must contain only numbers")
        @Size(min = 8, max = 8, message = "size must be {max}")
        String zipCode,

        @Schema(description = "The numerical addresses assigned to homes, businesses, and other properties", example = "966")
        @Size(min = 1, max = 10, message = "size must be between {min} and {max}")
        String number,

        @Schema(description = "Relatively permanent and highly organized centre of population", example = "São Paulo")
        @Size(min = 1, max = 100, message = "size must be between {min} and {max}")
        String city,

        @Schema(description = "Territory considered as an organized political community under brazilian government", example = "SP")
        @Pattern(regexp = "^[A-Z]{2}$", message = "incorrect state, must be in pattern UF")
        String state
) {
}
