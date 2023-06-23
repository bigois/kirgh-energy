package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * This code is defining an interface called {@code ApplianceRepository} that extends the {@code JpaRepository} interface. The
 * {@code JpaRepository} interface is a Spring Data interface that provides methods for performing CRUD (Create, Read, Update,
 * Delete) operations on entities. The {@code ApplianceRepository} interface is specifying that it will work with the {@code Appliance}
 * entity and use a {@code UUID} as the type for the entity's ID. This interface will allow the application to perform database
 * operations on the {@code Appliance} entity using Spring Data's built-in methods.
 */
public interface ApplianceRepository extends JpaRepository<Appliance, UUID> {
}
