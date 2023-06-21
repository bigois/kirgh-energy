package br.com.kirgh.app.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * This is a Java record class named {@code UserRelationDTO} that represents a data transfer object for a user relation. It has
 * two fields: {@code ownerId} and {@code relationType}.
 */
public record UserRelationDTO(
    @NotBlank(message = "cannot be null or empty")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "invalid parent id")
    String ownerId,

    @NotBlank(message = "cannot be null or empty")
    @Pattern(regexp = "[A-zÀ-ú\s]+", message = "must contain only letters")
    String relationType
) {
}
