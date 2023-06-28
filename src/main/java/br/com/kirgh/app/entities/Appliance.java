package br.com.kirgh.app.entities;

import br.com.kirgh.app.enums.Power;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * The Appliance class defines attributes and methods for an appliance object, including an ID, name,
 * brand, model, and power.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "appliances")
@SuppressWarnings("JpaDataSourceORMInspection")
public class Appliance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 150)
    private String brand;

    @Column(nullable = false, length = 150)
    private String model;

    @Column(nullable = false, length = 10, columnDefinition = "varchar(4)")
    @Enumerated(EnumType.STRING)
    private Power power;

    /**
     * This is an implementation of the equals method in Java that checks if two objects are equal based on their ID.
     *
     * @param o The parameter "o" is an object of type Object, which is the superclass of all Java classes. It is used to
     *          compare the equality of the current object with another object.
     * @return The {@code equals} method is returning a boolean value that indicates whether the current {@code Appliance} object is
     * equal to the object passed as an argument. It checks if the argument is null, if it is of the same class as the
     * current object, and if their {@code id} fields are equal. If all these conditions are met, it returns {@code true}, otherwise it
     * returns {@code false}.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appliance appliance = (Appliance) o;

        return id.equals(appliance.id);
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
