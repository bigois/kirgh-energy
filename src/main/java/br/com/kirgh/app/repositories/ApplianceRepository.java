package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.Appliance;
import br.com.kirgh.app.projections.ApplianceProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * This code is defining an interface called {@code ApplianceRepository} that extends the {@code JpaRepository} interface. The
 * {@code JpaRepository} interface is a Spring Data interface that provides methods for performing CRUD (Create, Read, Update,
 * Delete) operations on entities. The {@code ApplianceRepository} interface is specifying that it will work with the {@code Appliance}
 * entity and use a {@code UUID} as the type for the entity's ID. This interface will allow the application to perform database
 * operations on the {@code Appliance} entity using Spring Data's built-in methods.
 */
@SuppressWarnings("SqlResolve")
public interface ApplianceRepository extends JpaRepository<Appliance, UUID> {
    /**
     * The function returns a page of {@code Appliance} objects that match the given Specification and are
     * paginated according to the given {@code Pageable} object.
     *
     * @param spec        The "spec" parameter is a Specification object that represents the criteria or
     *                    conditions to be used for filtering the results of the query. It allows you to dynamically build
     *                    complex queries by combining multiple conditions using logical operators such as AND and OR.
     * @param pageRequest Pageable is an interface that represents a request for a specific page of
     *                    data. It includes information such as the page number, page size, and sorting criteria.
     * @return The method is returning a Page object containing a list of Appliance objects that match
     * the given Specification, with pagination applied according to the provided Pageable object.
     */
    Page<Appliance> findAll(Specification spec, Pageable pageRequest);

    /**
     * The function retrieves a list of appliances that are bound to a specific address.
     *
     * @param addressId The {@code addressId} parameter is a UUID (Universally Unique Identifier) that is used
     *                  to identify a specific address. It is used in the query to filter the appliances based on the
     *                  address they are bound to.
     * @return The query is returning a list of {@code ApplianceProjection} objects.
     */
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
    List<ApplianceProjection> getAllAppliancesBoundAddress(@Param("addressId") UUID addressId);

    /**
     * The code snippet you provided is a method declaration in the {@code ApplianceRepository} interface.
     * This method is using Spring Data's {@code @Transactional}, {@code @Modifying}, and {@code @Query} annotations to
     * define a custom delete operation.
     *
     * @param addressId The {@code addressId} parameter is a UUID (Universally Unique Identifier) that represents the unique
     *                  identifier of the address to be deleted.
     */
    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = """
                         DELETE FROM
                            appliances
                        WHERE id
                            IN(
                            SELECT
                                appliances.id
                            FROM
                                appliances
                            INNER JOIN
                                appliance_relations
                            ON
                                appliances.id = appliance_relations.appliance_id
                            WHERE
                                appliance_relations.address_id = :addressId
                            )
                    """
    )
    void deleteAppliancesByAddressId(@Param("addressId") UUID addressId);

    /**
     * The code snippet you provided is a method declaration in the {@code ApplianceRepository} interface.
     * This method is using Spring Data's {@code @Transactional}, {@code @Modifying}, and {@code @Query} annotations to
     * define a custom delete operation.
     *
     * @param applianceId The {@code applianceId} parameter is a UUID (Universally Unique Identifier) that represents the unique
     *                    identifier of the appliance to be deleted.
     */
    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = """
                        DELETE FROM
                            appliances
                        WHERE
                            id  = :applianceId
                    """
    )
    void deleteApplianceById(@Param("applianceId") UUID applianceId);
}
