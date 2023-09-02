package br.com.kirgh.app.projections;

import br.com.kirgh.app.enums.Power;

// The code is defining a Java interface called `ApplianceProjection`.
public interface ApplianceProjection {
    /**
     * The function getId() returns a byte array.
     * 
     * @return A byte array is being returned.
     */
    byte[] getId();

    /**
     * The function `getName()` returns a string.
     * 
     * @return The method `getName()` is returning a `String` value.
     */
    String getName();

    /**
     * The function "getBrand" returns a string representing the brand.
     * 
     * @return The getBrand() method returns a String value.
     */
    String getBrand();

    /**
     * The function getModel() returns a string representing the model.
     * 
     * @return The method `getModel()` is returning a string.
     */
    String getModel();

    /**
     * The function returns an object of type Power.
     * 
     * @return The function getPower() is returning a value of type Power.
     */
    Power getPower();
}
