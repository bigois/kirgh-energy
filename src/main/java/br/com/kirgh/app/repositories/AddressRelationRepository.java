package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.AddressRelation;
import br.com.kirgh.app.pks.AddressRelationPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This code is defining an interface called {@code AddressRelationRepository} that extends the {@code JpaRepository} interface. The
 * {@code JpaRepository} interface is a Spring Data JPA interface that provides methods for performing common database operations
 * on a specific entity. In this case, the {@code AddressRelationRepository} interface is specifying that it will be working with
 * the {@code AddressRelation} entity and its primary key {@code AddressRelationPK}. By extending the {@code JpaRepository} interface, the
 * {@code AddressRelationRepository} interface will inherit all of the methods provided by {@code JpaRepository} for working with the
 */
public interface AddressRelationRepository extends JpaRepository<AddressRelation, AddressRelationPK> {
}
