package br.com.kirgh.app.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class UserRelationPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false, foreignKey = @ForeignKey(name = "user_relations_owner_id_users_id_fk"))
    private User owner;

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false, foreignKey = @ForeignKey(name = "user_relations_child_id_users_id_fk"))
    private User child;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRelationPK that = (UserRelationPK) o;
        return owner.equals(that.owner) && child.equals(that.child);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, child);
    }
}
