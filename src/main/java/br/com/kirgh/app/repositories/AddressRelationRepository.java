package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.AddressRelation;
import br.com.kirgh.app.pks.AddressRelationPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * This code is defining an interface called {@code AddressRelationRepository} that extends the {@code JpaRepository} interface. The
 * {@code JpaRepository} interface is a Spring Data JPA interface that provides methods for performing common database operations
 * on a specific entity. In this case, the {@code AddressRelationRepository} interface is specifying that it will be working with
 * the {@code AddressRelation} entity and its primary key {@code AddressRelationPK}. By extending the {@code JpaRepository} interface, the
 * {@code AddressRelationRepository} interface will inherit all the methods provided by {@code JpaRepository} for working with the
 */
@SuppressWarnings("SqlResolve")
public interface AddressRelationRepository extends JpaRepository<AddressRelation, AddressRelationPK> {

    /**
     * The function deletes address relations based on the parent ID.
     *
     * @param parentId The {@code parentId} parameter is a UUID (Universally Unique Identifier) that represents the identifier of
     *                 the parent entity for which the addresses need to be deleted.
     */
    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = """
                        DELETE FROM
                            address_relations
                        WHERE
                            parent_id = :parentId
                    """
    )
    void deleteAddressesByParentId(@Param("parentId") UUID parentId);

    /**
     * The function deletes address relations based on the given address ID.
     *
     * @param addressId The {@code addressId} parameter is a UUID (Universally Unique Identifier) that represents the unique
     *                  identifier of an address.
     */
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
    void deleteAddressesByAddressId(@Param("addressId") UUID addressId);
}
