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
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false, foreignKey = @ForeignKey(name = "appliance_relations_address_id_addresses_id_fk"))
    private Address address;

    @ManyToOne
    @JoinColumn(name = "appliance_id", nullable = false, foreignKey = @ForeignKey(name = "appliance_relations_appliance_id_appliances_id_fk"))
    private Appliance appliance;
}
