package br.com.kirgh.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_relations")
public class UserRelation {
    @Autowired
    @EmbeddedId
    UserRelationPK userRelationPK = new UserRelationPK();

    @Column(length = 30, nullable = false)
    private String relationType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRelation that = (UserRelation) o;
        return userRelationPK.equals(that.userRelationPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRelationPK);
    }
}
