package br.com.kirgh.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Power {
    V110("110"),
    V220("220");

    private final String powerDescription;
}
