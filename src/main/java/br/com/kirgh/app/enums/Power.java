package br.com.kirgh.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This is a enum class that represents the power voltages from general appliances in Brazil.
 */
@AllArgsConstructor
@Getter
public enum Power {
    /**
     * {@code V110} is defining a value for appliances that require 110 volts of energy power.
     */
    V110("110"),

    /**
     * {@code V220} is defining a value for appliances that require 220 volts of energy power.
     */
    V220("220");

    private final String powerDescription;
}
