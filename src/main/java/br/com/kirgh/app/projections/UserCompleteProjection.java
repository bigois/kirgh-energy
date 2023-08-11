package br.com.kirgh.app.projections;

import java.util.Date;

import br.com.kirgh.app.enums.UserGender;

public interface UserCompleteProjection {
    byte[] getId();

    String getName();

    Date getBirthDate();

    String getGender();

    String getCpf();

    String getEmail();
}
