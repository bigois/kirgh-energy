package br.com.kirgh.app.mappers;

import br.com.kirgh.app.dtos.AddressDTO;
import br.com.kirgh.app.dtos.AddressUpdateDTO;
import br.com.kirgh.app.entities.Address;
import br.com.kirgh.app.enums.State;

/**
 * The AddressMapper class provides a static method to convert an AddressDTO object to an Address
 * object in Java.
 */
public abstract class AddressMapper {
    /**
     * The function converts an AddressDTO object to an Address object in Java.
     *
     * @param addressDTO AddressDTO is an object that represents an address in a data transfer object (DTO) format. It
     *                   contains the following properties: street, zipCode, number, city, and state.
     * @return An instance of the {@code Address} class is being returned.
     */
    public static Address addressDTOToAddress(AddressDTO addressDTO) {
        Address address = new Address();

        address.setStreet(addressDTO.street());
        address.setZipCode(addressDTO.zipCode());
        address.setNumber(addressDTO.number());
        address.setCity(addressDTO.city());
        address.setState(addressDTO.state());

        return address;
    }

    /**
     * The function takes an AddressUpdateDTO object and updates an existing Address object with the
     * values from the DTO.
     * 
     * @param addressUpdateDTO An object of type AddressUpdateDTO, which contains the updated address
     * information.
     * @param updateAddress The `updateAddress` parameter is an instance of the `Address` class that
     * needs to be updated with the values from the `AddressUpdateDTO` object.
     * @return The method is returning the updated address object.
     */
    public static Address addressUpdateDTOToAddress(AddressUpdateDTO addressUpdateDTO, Address updateAddress) {
        if (addressUpdateDTO.street() != null) {
            updateAddress.setStreet(addressUpdateDTO.street());
        }

        if (addressUpdateDTO.number() != null) {
            updateAddress.setNumber(addressUpdateDTO.number());
        }

        if (addressUpdateDTO.zipCode() != null) {
            updateAddress.setZipCode(addressUpdateDTO.zipCode());
        }

        if (addressUpdateDTO.city() != null) {
            updateAddress.setCity(addressUpdateDTO.city());
        }

        if (addressUpdateDTO.state() != null) {
            updateAddress.setState(State.valueOf(addressUpdateDTO.state()));
        }

        return updateAddress;
    }
}
