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
public class AddressRelationPK implements Serializable {
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false, foreignKey = @ForeignKey(name = "address_relations_parent_id_users_id_fk"))
    private User parent;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false, foreignKey = @ForeignKey(name = "address_relations_address_id_addresses_id_fk"))
    private Address address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressRelationPK that = (AddressRelationPK) o;
        return parent.equals(that.parent) && address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parent, address);
    }
}
