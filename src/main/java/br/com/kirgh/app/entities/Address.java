package br.com.kirgh.app.entities;

import br.com.kirgh.app.enums.State;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Address class represents a physical address with fields for zip code, street, number, city, and
 * state, and includes methods for checking equality and generating hash codes based on the id field.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "addresses")
@Schema(title = "Address", description = "Object that represents a entity address")
@SuppressWarnings("JpaDataSourceORMInspection")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 8)
    private String zipCode;

    @Column(nullable = false, length = 150)
    private String street;

    @Column(nullable = false, length = 10)
    private String number;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 2)
    @Enumerated(EnumType.STRING)
    private State state;

    /**
     * This is an implementation of the equals method in Java that checks if two Address objects are equal based on their
     * id field.
     *
     * @param o The parameter "o" is an object of type Object, which is the superclass of all other classes in Java. It is
     *          used to compare the equality of two Address objects in the equals() method.
     * @return The {@code equals} method is returning a boolean value that indicates whether the current {@code Address} object is
     * equal to the object passed as an argument. It checks if the argument is null or not an instance of {@code Address} class,
     * and then compares the {@code id} field of both objects to determine their equality. If the {@code id} fields are equal, it
     * returns {@code true}, otherwise it returns {@code}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return id.equals(address.id);
    }

    /**
     * This function returns the hash code of the "id" attribute.
     *
     * @return The {@code hashCode()} method is returning the hash code of the {@code id} object. The hash code is an integer value
     * that is used to identify objects in hash-based data structures such as hash tables.
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
