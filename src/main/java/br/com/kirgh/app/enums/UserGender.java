package br.com.kirgh.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserGender {
    M("Male"),
    F("Female");

    private final String genderDescription;
}
