package br.com.kirgh.app.dtos;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRelationInfoDTO{
    private UUID id;
    private String relationType;

    public UserRelationInfoDTO(UUID child_id, String relation_type){
        id = child_id;
        relationType = relation_type;
    }
}