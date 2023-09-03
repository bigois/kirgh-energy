package br.com.kirgh.app.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
/*
  The UserRelationInfoDTO class represents information about a user's relation, including an ID and a
  relation type.
 */
public class UserRelationInfoDTO {
    private UUID id;
    private String relationType;

    public UserRelationInfoDTO(UUID child_id, String relation_type) {
        id = child_id;
        relationType = relation_type;
    }
}
