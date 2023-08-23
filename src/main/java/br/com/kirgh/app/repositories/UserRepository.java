package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * This line of code is defining an interface called {@code UserRepository} that extends the {@code JpaRepository}
 * interface. The {@code JpaRepository} interface is a Spring Data interface that provides methods for
 * performing common database operations on a specific entity type ({@code User} in this case). The second
 * parameter of {@code JpaRepository} ({@code UUID}) specifies the type of the primary key for the {@code User} entity.
 * Therefore, {@code UserRepository} inherits all the methods of {@code JpaRepository} and can also define
 * additional methods specific to the {@code User} entity.
 */
public interface UserRepository extends JpaRepository<User, UUID> {
    /**
     * The function checks if an email exists in a database or list.
     *
     * @param email The email parameter is a string that represents an email address. The method existsByEmail checks if
     *              there is any record in a database or list that matches the given email address. It returns a boolean value, true if
     *              the email exists in the database or list, and false if it does not exist.
     * @return A boolean value is being returned. The method is checking whether an email exists in a system or database
     * and will return true if it does exist and false if it does not exist.
     */
    boolean existsByEmail(String email);

    /**
     * The function checks if a record exists in a database by its CPF (Brazilian ID) number.
     *
     * @param cpf The "cpf" parameter is a string representing a Brazilian individual taxpayer registry identification
     *            number, also known as "Cadastro de Pessoas Físicas" (CPF). It is a unique identifier assigned to each Brazilian
     *            citizen or resident for tax and administrative purposes. The method "existsByCpf" is
     * @return A boolean value is being returned. The method name "existsByCpf" suggests that it checks whether a record
     * exists in a database or collection based on a given CPF (Brazilian individual taxpayer registry identification)
     * number. If a record with the given CPF exists, the method will return true, otherwise it will return false.
     */
    boolean existsByCpf(String cpf);

    Page<User> findAll(Specification spec, Pageable pageable);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,
            value = """
                        DELETE FROM
                            users
                        WHERE
                            id  = :userId
                    """
    )
    void deleteUserById(@Param("userId") UUID userId);


}
