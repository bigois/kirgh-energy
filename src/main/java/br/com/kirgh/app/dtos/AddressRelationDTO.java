package br.com.kirgh.app.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

/**
 * This is a Java record class named {@code AddressRelationDTO} that has a single field named {@code parentId} of type {@code String}. The
 * field is annotated with validation constraints using the Jakarta Bean Validation API. The {@code @NotBlank} annotation ensures
 * that the field is not null or empty, the {@code @Size} annotation ensures that the field has a length of exactly 11
 * characters, and the {@code @CPF} annotation ensures that the field is a valid Brazilian CPF number. The record class is
 * immutable and automatically generates a constructor, getters, and {@code equals()} and {@code hashCode()} methods based on the
 */
public record AddressRelationDTO(
    @NotBlank(message = "cannot be null or empty")
    @Size(min = 11, max = 11, message = "must have {min} characters")
    @CPF
    String parentId
) {
}
