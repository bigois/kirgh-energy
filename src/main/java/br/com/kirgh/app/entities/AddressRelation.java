package br.com.kirgh.app.entities;

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
@Table(name = "address_relations")
public class AddressRelation {
    @Autowired
    @EmbeddedId
    AddressRelationPK addressRelationPK = new AddressRelationPK();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressRelation that = (AddressRelation) o;
        return addressRelationPK.equals(that.addressRelationPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressRelationPK);
    }
}
