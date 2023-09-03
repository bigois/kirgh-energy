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
 * The {@code AddressRelation} class in Java defines methods for checking equality and generating hash codes
 * based on its embedded ID field.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address_relations")
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class AddressRelation {
    @Autowired
    @EmbeddedId
    AddressRelationPK addressRelationPK = new AddressRelationPK();

    /**
     * This is an implementation of the equals method in Java that checks if two AddressRelation objects
     * are equal based on their {@code addressRelationPK} field.
     *
     * @param o The parameter "o" is an {@code Object} type, which is the object being compared to the current
     *          object for equality in the {@code equals()} method.
     * @return A boolean value indicating whether the current object is equal to the object passed as a
     * parameter.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressRelation that = (AddressRelation) o;

        return addressRelationPK.equals(that.addressRelationPK);
    }

    /**
     * This function returns the hash code of the {@code addressRelationPK} object.
     *
     * @return The {@code hashCode()} method is returning the hash code value of the {@code addressRelationPK} object.
     */
    @Override
    public int hashCode() {
        return addressRelationPK.hashCode();
    }
}
