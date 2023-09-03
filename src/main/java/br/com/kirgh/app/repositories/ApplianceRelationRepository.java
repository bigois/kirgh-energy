package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.ApplianceRelation;
import br.com.kirgh.app.pks.ApplianceRelationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * This code is defining an interface called {@code ApplianceRelationRepository} that extends the
 * {@code JpaRepository} interface. The {@code JpaRepository} interface is a Spring Data JPA interface that
 * provides methods for performing CRUD (Create, Read, Update, Delete) operations on entities. By
 * extending this interface, the {@code ApplianceRelationRepository} interface inherits these methods and can
 * {@code ApplianceRelationPK}.
 */
@SuppressWarnings("SqlResolve")
public interface ApplianceRelationRepository extends JpaRepository<ApplianceRelation, ApplianceRelationPK> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = """
                        DELETE FROM
                            appliance_relations
                        WHERE
                            address_id = :addressId
                    """
    )
    void deleteApplianceRelationByAddressId(@Param("addressId") UUID addressId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = """
                        DELETE FROM
                            appliance_relations
                        WHERE
                            appliance_id = :applianceId
                    """
    )
    void deleteApplianceRelationById(@Param("applianceId") UUID applianceId);

}
