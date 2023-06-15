package br.com.kirgh.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.kirgh.app.entities.ApplianceRelation;
import br.com.kirgh.app.entities.ApplianceRelationPK;

public interface ApplianceRelationRepository extends JpaRepository<ApplianceRelation, ApplianceRelationPK> {
    
}
