package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.UserRelation;
import br.com.kirgh.app.pks.UserRelationPK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This code is defining a Spring Data JPA repository interface for the {@code UserRelation} entity. The interface extends the
 * {@code JpaRepository} interface, which provides basic CRUD (Create, Read, Update, Delete) operations for the entity. The
 * {@code UserRelationPK} is the primary key class for the {@code UserRelation} entity, and it is used as the second generic type
 * parameter of the {@code JpaRepository} interface. This allows the repository to handle composite primary keys.
 */
public interface UserRelationRepository extends JpaRepository<UserRelation, UserRelationPK> {
}
