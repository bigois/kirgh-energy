package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.Address;
import br.com.kirgh.app.projections.AddressProjection;

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
 * This code is defining an interface called {@code AddressRepository} that extends the {@code JpaRepository}
 * interface. The {@code JpaRepository} interface is a Spring Data interface that provides methods for
 * performing CRUD (Create, Read, Update, Delete) operations on a specific entity type (`Address` in
 * this case) in a database. The {@code UUID} parameter specifies the type of the primary key for the
 * {@code Address} entity.
 */
@SuppressWarnings("SqlResolve")
public interface AddressRepository extends JpaRepository<Address, UUID> {
    /**
     * This is a Java function that checks if a specific address exists for a given user based on their ID, zip code, and
     * number.
     *
     * @param userId  a String representing the ID of a user
     * @param zipCode The zip code of an address.
     * @param number  The parameter "number" is a String representing the street number of an address.
     * @return A boolean value is being returned.
     */

    Page<Address> findAll(Pageable pageable);
    Page<Address> findAll(Specification spec, Pageable pageRequest);
    @Query(nativeQuery = true,
            value = """
                        SELECT
                            CASE
                                WHEN (COUNT(1) > 0) THEN
                                true
                            ELSE
                                false
                            END
                        FROM
                            address_relations rel
                            INNER JOIN
                                addresses adr
                                ON
                                    adr.id = rel.address_id
                                    AND zip_code = :zipCode
                                    AND number = :number
                        WHERE
                            parent_id = :userId
                    """
    )
    boolean existsToUserByUnique(@Param("userId") UUID userId, @Param("zipCode") String zipCode, @Param("number") String number);

    @Query(nativeQuery = true,
            value = """
                        SELECT
                            id,
                            street,
                            number,
                            zip_code,
                            city,
                            state
                        FROM
                            addresses
                        WHERE
                            id = :id
                    """
    )
    AddressProjection getAllAddressInfoById(@Param("id") UUID id);

    @Query(nativeQuery = true,
            value = """
                        SELECT
                            addresses.id, addresses.zip_code, addresses.street, addresses.number, addresses.city, addresses.state
                        FROM
                            addresses
                        INNER JOIN
                            address_relations
                        ON
                            addresses.id = address_relations.address_id
                        WHERE
                            address_relations.parent_id = :parentId
                    """
    )
    List<AddressProjection> getAllAddressesBoundUser(@Param("parentId") UUID parentId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = """
                        DELETE FROM
                            address_relations
                        WHERE
                            address_id = :addressId
                    """
    )
    void deleteAddressRelationById(@Param("addressId") UUID addressId);

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
    void deleteApplianceRelationById(@Param("addressId") UUID addressId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = """
                        DELETE FROM
                            addresses
                        WHERE
                            id  = :addressId
                    """
    )
    void deleteAddressById(@Param("addressId") UUID addressId);
}
