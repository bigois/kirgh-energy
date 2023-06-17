package br.com.kirgh.app.entities;

import br.com.kirgh.app.pks.ApplianceRelationPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is a Java class representing a relation between appliances, with methods for checking equality and generating hash
 * codes.
 */
@Getter
@Setter
@Entity
@Table(name = "appliance_relations")
public class ApplianceRelation {
    @Autowired
    @EmbeddedId
    ApplianceRelationPK applianceRelationPK = new ApplianceRelationPK();

    /**
     * This is an implementation of the equals method in Java that checks if two objects of the same class have equal
     * primary keys.
     *
     * @param o The parameter "o" is an object of type Object, which is the superclass of all other classes in Java. It is
     *          used to compare the equality of two objects of the ApplianceRelation class.
     * @return The {@code equals} method is returning a boolean value, which indicates whether the current object is equal to the
     * object passed as an argument.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplianceRelation that = (ApplianceRelation) o;

        return applianceRelationPK.equals(that.applianceRelationPK);
    }

    /**
     * This function returns the hash code of the applianceRelationPK object.
     *
     * @return The {@code hashCode()} method is returning the hash code value of the {@code applianceRelationPK} object.
     */
    @Override
    public int hashCode() {
        return applianceRelationPK.hashCode();
    }
}
