package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.AddressRelation;
import br.com.kirgh.app.entities.AddressRelationPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRelationRepository extends JpaRepository<AddressRelation, AddressRelationPK> {
}
