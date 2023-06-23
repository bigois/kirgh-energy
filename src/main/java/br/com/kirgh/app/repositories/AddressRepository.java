package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

/**
 * This code is defining an interface called {@code AddressRepository} that extends the {@code JpaRepository}
 * interface. The {@code JpaRepository} interface is a Spring Data interface that provides methods for
 * performing CRUD (Create, Read, Update, Delete) operations on a specific entity type (`Address` in
 * this case) in a database. The {@code UUID} parameter specifies the type of the primary key for the
 * {@code Address} entity.
 */
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
}
