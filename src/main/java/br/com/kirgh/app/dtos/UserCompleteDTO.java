package br.com.kirgh.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

@Schema(title = "UserComplete", description = "Object that represents a data transfer object for all user data")
public record UserCompleteDTO(
        @NotBlank(message = "cannot be null or empty")
        @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "invalid user id")
        @Size(min = 36, max = 36, message = "size must be {max}")
        @Schema(description = "UUID from an existent user", example = "67459848-3af5-4c99-9276-543c331adcc1")
        UUID id,

        @NotBlank(message = "cannot be null or empty")
        @Size(min = 2, max = 150, message = "size must be between {min} and {max}")
        @Pattern(regexp = "[A-zÀ-ú\s]+", message = "must contain only letters")
        @Schema(description = "Name to identify an user", example = "Sophie Giovanna da Rocha")
        String name
) {
}
