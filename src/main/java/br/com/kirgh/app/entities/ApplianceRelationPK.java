package br.com.kirgh.app.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@Embeddable
public class ApplianceRelationPK {
    
    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false, foreignKey = @ForeignKey(name = "appliance_relations_address_id_addresses_id_fk"))
    private Address address;

    @ManyToOne
    @JoinColumn(name = "appliance_id", nullable = false, foreignKey = @ForeignKey(name = "appliance_relations_appliance_id_appliances_id_fk"))
    private Appliance appliance;

}
