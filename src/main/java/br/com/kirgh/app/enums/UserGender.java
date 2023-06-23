package br.com.kirgh.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This is a enum class that represents the biological human genders.
 */
@AllArgsConstructor
@Getter
public enum UserGender {
    /**
     * {@code M} is defining a value for the biological gender of male (XY chromosome).
     */
    M("Male"),

    /**
     * {@code F} is defining a value for the biological gender of female (XX chromosome).
     */
    F("Female");

    private final String genderDescription;
}
