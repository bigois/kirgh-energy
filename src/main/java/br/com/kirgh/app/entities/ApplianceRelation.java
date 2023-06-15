package br.com.kirgh.app.entities;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "appliance_relations")
public class ApplianceRelation {
    @Autowired
    @EmbeddedId
    ApplianceRelationPK applianceRelationPK = new ApplianceRelationPK();
}
