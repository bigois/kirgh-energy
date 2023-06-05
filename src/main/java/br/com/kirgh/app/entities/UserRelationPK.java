package br.com.kirgh.app.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class UserRelationPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false, foreignKey = @ForeignKey(name = "user_relations_owner_id_users_id_fk"))
    private User owner;

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false, foreignKey = @ForeignKey(name = "user_relations_child_id_users_id_fk"))
    private User child;
}
