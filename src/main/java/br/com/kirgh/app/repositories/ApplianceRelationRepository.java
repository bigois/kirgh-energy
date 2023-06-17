package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.ApplianceRelation;
import br.com.kirgh.app.pks.ApplianceRelationPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This code is defining an interface called {@code ApplianceRelationRepository} that extends the {@code JpaRepository} interface. The
 * {@code JpaRepository} interface is a Spring Data JPA interface that provides methods for performing CRUD (Create, Read,
 * Update, Delete) operations on entities.
 */
public interface ApplianceRelationRepository extends JpaRepository<ApplianceRelation, ApplianceRelationPK> {
}
