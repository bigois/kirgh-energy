package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.Appliance;
import br.com.kirgh.app.projections.AddressProjection;
import br.com.kirgh.app.projections.ApplianceProjection;
import br.com.kirgh.app.projections.UserCompleteProjection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * This code is defining an interface called {@code ApplianceRepository} that extends the {@code JpaRepository} interface. The
 * {@code JpaRepository} interface is a Spring Data interface that provides methods for performing CRUD (Create, Read, Update,
 * Delete) operations on entities. The {@code ApplianceRepository} interface is specifying that it will work with the {@code Appliance}
 * entity and use a {@code UUID} as the type for the entity's ID. This interface will allow the application to perform database
 * operations on the {@code Appliance} entity using Spring Data's built-in methods.
 */
public interface ApplianceRepository extends JpaRepository<Appliance, UUID> {

    @Query(nativeQuery = true,
            value = """
                        SELECT
                            id,
                            name,
                            brand,
                            model,
                            power
                        FROM
                            appliances
                        WHERE
                            id = :id
                    """
    )
    ApplianceProjection getAllApplianceInfoById(@Param("id") UUID id);

    @Query(nativeQuery = true,
            value = """
                    SELECT 
                         appliances.id, appliances.name, appliances.brand, appliances.model, appliances.power
                    FROM 
                         appliances 
                    INNER JOIN 
                         appliance_relations 
                    ON 
                         appliances.id = appliance_relations.appliance_id 
                    WHERE  
                         appliance_relations.address_id = :addressId
                    """
    )
    List<ApplianceProjection> getAllAppliancesBoundUser(@Param("addressId") UUID addressId);
}
