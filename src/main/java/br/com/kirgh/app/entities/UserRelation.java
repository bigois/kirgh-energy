package br.com.kirgh.app.entities;

import br.com.kirgh.app.pks.UserRelationPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is a Java class representing a user relation entity with a primary key and methods for checking equality and
 * generating hash codes.
 */
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

    /**
     * This is an implementation of the equals method in Java that checks if two UserRelation objects are equal based on
     * their userRelationPK attribute.
     *
     * @param o The parameter "o" is an object of type Object, which is the superclass of all other classes in Java. In
     *          this case, it is used to compare the equality of two UserRelation objects.
     * @return The {@code equals} method is returning a boolean value. It returns {@code true} if the object being compared is equal to
     * the current object, and {@code false} otherwise. The equality is determined based on whether the {@code userRelationPK} field of
     * the two objects is equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRelation that = (UserRelation) o;

        return userRelationPK.equals(that.userRelationPK);
    }

    /**
     * This function returns the hash code of a user relation primary key.
     *
     * @return The {@code hashCode()} method is returning the hash code value of the {@code userRelationPK} object.
     */
    @Override
    public int hashCode() {
        return userRelationPK.hashCode();
    }
}
