package br.com.kirgh.app.entities;

import br.com.kirgh.app.pks.AddressRelationPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This is a Java class representing an address relation with methods for checking equality and returning hash code.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address_relations")
public class AddressRelation {
    @Autowired
    @EmbeddedId
    AddressRelationPK addressRelationPK = new AddressRelationPK();

    /**
     * This is an implementation of the equals method in Java that checks if two AddressRelation objects are equal based on
     * their addressRelationPK field.
     *
     * @param o The parameter "o" is an object of type Object, which is the superclass of all other classes in Java. In
     *          this case, it is being used as a parameter for the equals() method, which is being overridden in the current class.
     *          The purpose of this method is to compare the current object
     * @return The {@code equals} method is returning a boolean value, which indicates whether the current object is equal to the
     * object passed as an argument.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressRelation that = (AddressRelation) o;

        return addressRelationPK.equals(that.addressRelationPK);
    }

    /**
     * This function returns the hash code of the addressRelationPK object.
     *
     * @return The {@code hashCode()} method is returning the hash code value of the {@code addressRelationPK} object.
     */
    @Override
    public int hashCode() {
        return addressRelationPK.hashCode();
    }
}
