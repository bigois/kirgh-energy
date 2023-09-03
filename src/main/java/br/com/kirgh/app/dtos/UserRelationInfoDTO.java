package br.com.kirgh.app.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * The {@code UserRelationInfoDTO} class represents information about a user's relation, including an ID and a
 * relation type.
 */
@Getter
@Setter
public class UserRelationInfoDTO {
    private UUID id;
    private String relationType;

    /**
     * This method is a constructor for the {@code UserRelationInfoDTO} class.
     *
     * @param id           The {@code id} parameter is a UUID (Universally Unique Identifier) that represents the unique
     *                     identifier of an existing user.
     * @param relationType The {@code relationType} parameter is a text that represents the relations between the parent user and child user (e.g.: "Son", "Daughter")
     */
    public UserRelationInfoDTO(UUID id, String relationType) {
        this.id = id;
        this.relationType = relationType;
    }
}
