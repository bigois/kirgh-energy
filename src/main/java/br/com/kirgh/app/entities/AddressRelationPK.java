package br.com.kirgh.app.entities;

import java.io.Serializable;

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
public class AddressRelationPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false, foreignKey = @ForeignKey(name = "address_relations_parent_id_users_id_fk"))
    private User parent;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false, foreignKey = @ForeignKey(name = "address_relations_address_id_addresses_id_fk"))
    private Address address;
}

