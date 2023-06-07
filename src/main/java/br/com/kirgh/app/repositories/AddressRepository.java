package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(nativeQuery = true,
            value = """
                        select
                            case
                                when (count(1) > 0) then
                                true
                            else
                                false
                            end
                        from
                            address_relations rel
                            inner join
                                addresses adr
                                on
                                    adr.id = rel.address_id
                                    and zip_code = :zipcode
                                    and number = :number
                        where
                            parent_id = :userid
                    """
    )
    boolean existsToUserByUnique(@Param("userId") String userId, @Param("zipCode") String zipCode, @Param("number") String number);
}
