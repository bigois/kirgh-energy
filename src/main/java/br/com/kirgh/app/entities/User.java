package br.com.kirgh.app.entities;

import br.com.kirgh.app.enums.UserGender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

/**
 * The User class represents a user with attributes such as name, email, birthdate, gender, and CPF,
 * and overrides the equals and hashCode methods to compare and hash based on the CPF attribute.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@SuppressWarnings("JpaDataSourceORMInspection")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "cpf", length = 11, unique = true)
    private String cpf;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 80, unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "date")
    private LocalDate birthDate;

    @Column(nullable = false, length = 10, columnDefinition = "varchar(1)")
    @Enumerated(EnumType.STRING)
    private UserGender gender;

    /**
     * This is an implementation of the equals method in Java that checks if two User objects have the same CPF (a unique
     * identifier for individuals in Brazil).
     *
     * @param o The parameter "o" is an object of type Object, which is the superclass of all classes in Java. It is used
     *          to compare the equality of two objects of the User class.
     * @return The {@code equals} method is returning a boolean value, which indicates whether the current {@code User} object is equal
     * to the object passed as a parameter. The method checks if the parameter is null or not an instance of the {@code User}
     * class, and then compares the {@code cpf} field of both objects to determine if they are equal. If the {@code cpf} fields are
     * equal, the method returns {@code}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return cpf.equals(user.cpf);
    }

    /**
     * This function overrides the default hashCode() method to return the hash code of the object's cpf attribute.
     *
     * @return The {@code hashCode()} method is returning the hash code of the {@code cpf} attribute.
     */
    @Override
    public int hashCode() {
        return cpf.hashCode();
    }
}
