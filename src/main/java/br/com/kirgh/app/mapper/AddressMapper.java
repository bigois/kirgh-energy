package br.com.kirgh.app.mapper;

import br.com.kirgh.app.dtos.AddressDTO;
import br.com.kirgh.app.entities.Address;

public abstract class AddressMapper {
    public static Address addressTOToAddress(AddressDTO addressDTO) {
        Address address = new Address();
        address.setStreet(addressDTO.street());
        address.setZipCode(addressDTO.zipCode());
        address.setNumber(addressDTO.number());
        address.setCity(addressDTO.city());
        address.setState(addressDTO.state());

        return address;
    }

}
