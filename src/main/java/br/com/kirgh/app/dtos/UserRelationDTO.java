package br.com.kirgh.app.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

/**
 * This is a Java record class named {@code UserRelationDTO} that represents a data transfer object for a user relation. It has
 * two fields: {@code ownerId} and {@code relationType}.
 */
public record UserRelationDTO(
    @NotBlank(message = "cannot be null or empty")
    @Size(min = 11, max = 11, message = "must have {min} characters")
    @CPF
    String ownerId,

    @NotBlank(message = "cannot be null or empty")
    @Pattern(regexp = "[A-zÀ-ú\s]+", message = "must contain only letters")
    String relationType
) {
}
