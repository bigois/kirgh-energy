package br.com.kirgh.app.projections;

import java.util.Date;

public interface UserProjection {
    byte[] getId();
    String getName();
    Date getBirthDate();
    String getGender();
    String getCpf();
    String getEmail();
}
