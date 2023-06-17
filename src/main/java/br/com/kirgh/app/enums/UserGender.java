package br.com.kirgh.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This is a Java enum class called {@code UserGender} that defines two constants {@code M} and {@code F} with corresponding
 * {@code genderDescription} values of "Male" and "Female", respectively.
 */
@AllArgsConstructor
@Getter
public enum UserGender {
    M("Male"),
    F("Female");

    private final String genderDescription;
}
