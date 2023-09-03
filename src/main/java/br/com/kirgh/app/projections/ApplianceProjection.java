package br.com.kirgh.app.projections;

import br.com.kirgh.app.enums.Power;

/**
 * The code is defining a Java interface called {@code ApplianceProjection}.
 */
public interface ApplianceProjection {
    /**
     * The function {@code getId()} returns a byte array.
     *
     * @return A byte array is being returned.
     */
    byte[] getId();

    /**
     * The function {@code getName()} returns a string.
     *
     * @return The method {@code getName()} is returning a {@code String} value.
     */
    String getName();

    /**
     * The function {@code getBrand} returns a string representing the brand.
     *
     * @return The {@code getBrand()} method returns a {@code String} value.
     */
    String getBrand();

    /**
     * The function {@code getModel()} returns a string representing the model.
     *
     * @return The method {@code getModel()} is returning a string.
     */
    String getModel();

    /**
     * The function returns an object of type {@code Power}.
     *
     * @return The function {@code getPower()} is returning a value of type {@code Power}.
     */
    Power getPower();
}
