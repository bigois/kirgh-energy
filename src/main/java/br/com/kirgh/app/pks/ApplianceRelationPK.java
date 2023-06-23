package br.com.kirgh.app.pks;

import br.com.kirgh.app.entities.Address;
import br.com.kirgh.app.entities.Appliance;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * This is a Java class representing a composite primary key for a many-to-one relationship between an Address and an
 * Appliance entity.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class ApplianceRelationPK implements Serializable {
    /**
     * This code is defining a many-to-one relationship between the {@code ApplianceRelationPK} entity and
     * the {@code Address} entity.
     */
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false, foreignKey = @ForeignKey(name = "appliance_relations_address_id_addresses_id_fk"))
    private Address address;

    /**
     * This code is defining a many-to-one relationship between the `ApplianceRelationPK` entity and
     * the {@code Appliance} entity.
     */
    @ManyToOne
    @JoinColumn(name = "appliance_id", nullable = false, foreignKey = @ForeignKey(name = "appliance_relations_appliance_id_appliances_id_fk"))
    private Appliance appliance;

    /**
     * This is an implementation of the equals method for a class called ApplianceRelationPK that checks if two objects are
     * equal based on their address and appliance attributes.
     *
     * @param o The parameter "o" is an object of type Object, which is the superclass of all other classes in Java. In
     *          this case, it is used to compare the current object with another object to check if they are equal.
     * @return A boolean value indicating whether the object being compared to is equal to the current object or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplianceRelationPK that = (ApplianceRelationPK) o;

        if (!address.equals(that.address)) return false;
        return appliance.equals(that.appliance);
    }

    /**
     * This is an implementation of the hashCode() method in Java that returns a unique hash code based on the combination
     * of the hash codes of the address and appliance objects.
     *
     * @return The {@code hashCode()} method is returning an integer value that is calculated based on the hash codes of the
     * {@code address} and {@code appliance} objects. The calculation involves multiplying the hash code of {@code address} by 31 and adding
     * the hash code of {@code appliance}.
     */
    @Override
    public int hashCode() {
        int result = address.hashCode();
        result = 31 * result + appliance.hashCode();
        return result;
    }
}
