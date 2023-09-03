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
    /**
     * The function deletes appliance relations based on the given address ID.
     *
     * @param addressId The {@code addressId} parameter is a UUID (Universally Unique Identifier) that represents the unique
     *                  identifier of an address.
     */
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

    /**
     * The function deletes a row from the {@code appliance_relations} table based on the provided {@code applianceId}.
     *
     * @param applianceId The {@code applianceId} parameter is a UUID (Universally Unique Identifier) that represents the unique
     *                    identifier of an appliance.
     */
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
