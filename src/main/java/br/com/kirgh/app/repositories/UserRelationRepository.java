package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.UserRelation;
import br.com.kirgh.app.entities.UserRelationPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRelationRepository extends JpaRepository<UserRelation, UserRelationPK> {
}
