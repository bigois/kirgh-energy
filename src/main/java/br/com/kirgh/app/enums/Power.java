package br.com.kirgh.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This is a Java enum class called {@code Power} that defines two constants {@code V110} and {@code V220}. Each constant has a corresponding
 * {@code powerDescription} field that is set to either "110" or "220".
 */
@AllArgsConstructor
@Getter
public enum Power {
    V110("110"),
    V220("220");

    private final String powerDescription;
}
