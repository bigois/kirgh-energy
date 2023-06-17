package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface called {@code UserRepository} that extends the {@code JpaRepository} interface. The {@code JpaRepository} interface is
 * a Spring Data interface that provides methods for performing common database operations on a specific entity type
 * (}User} in this case). The second parameter of {@code JpaRepository} (}String}) specifies the type of the primary key for
 * the {@code User} entity. Therefore, {@code UserRepository} inherits all the methods of {@code JpaRepository} and can also define
 * additional methods specific to the {@code User} entity.
 */
public interface UserRepository extends JpaRepository<User, String> {
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
}
