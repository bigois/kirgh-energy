package br.com.kirgh.app.projections;

import br.com.kirgh.app.enums.State;

public interface AddressProjection {
    
    byte[] getId();
    String getStreet();
    String getNumber();
    String getZipCode();
    String getCity();
    State getState();

}
