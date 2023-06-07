package br.com.kirgh.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kirgh.app.entities.AddressRelation;
import br.com.kirgh.app.entities.AddressRelationPK;

public interface AddressRelationRepository extends JpaRepository<AddressRelation, AddressRelationPK> {
    
}
