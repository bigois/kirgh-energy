package br.com.kirgh.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.kirgh.app.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long>  {

    @Query(nativeQuery = true,
            value =
                """
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

    boolean existsToUserByUnique(@Param("userId") String userId, @Param("zipCode") String zipCode, @Param("number") String number);
}