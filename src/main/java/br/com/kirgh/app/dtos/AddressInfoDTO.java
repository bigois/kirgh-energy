package br.com.kirgh.app.dtos;

import br.com.kirgh.app.enums.State;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

// The code snippet is defining a Java record class called `AddressInfoDTO`. A record is a new feature
// introduced in Java 14 that combines the features of a class and a data class. It is used to create
// simple classes that mainly hold data.
public record AddressInfoDTO(
        @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "invalid user id")
        @Size(min = 36, max = 36, message = "size must be {max}")
        @Schema(description = "UUID from an existent user", example = "67459848-3af5-4c99-9276-543c331adcc1")
        UUID id,

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
        State state
) {
}
