package br.com.kirgh.app.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * This is a Java record class named {@code UserRelationDTO} that represents a data transfer object for a user relation. It has
 * two fields: {@code ownerId} and {@code relationType}.
 */
@Schema(title = "UserRelation", description = "Object that represents a data transfer object for a user relation")
public record UserRelationDTO(
    @NotBlank(message = "cannot be null or empty")
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$", message = "invalid parent id")
    @Size(min = 36, max = 36, message = "size must be {max}")
    @Schema(description = "UUID from an existent user")
    String ownerId,

    @NotBlank(message = "cannot be null or empty")
    @Pattern(regexp = "[A-zÀ-ú\\s]+", message = "must contain only letters")
    @Size(min = 1, max = 30, message = "size must be between {min} and {max}")
    @Schema(description = "Specifies the degree of kinship")
    String relationType
) {
}
