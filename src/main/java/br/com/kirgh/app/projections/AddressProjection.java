package br.com.kirgh.app.projections;

import br.com.kirgh.app.enums.State;

public interface AddressProjection {
    
    byte[] getId();

    String getZipCode();

    String getStreet();

    String getNumber();

    String getCity();

    State getState();
}
