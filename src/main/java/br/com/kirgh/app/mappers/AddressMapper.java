package br.com.kirgh.app.mappers;

import br.com.kirgh.app.dtos.AddressDTO;
import br.com.kirgh.app.dtos.AddressUpdateDTO;
import br.com.kirgh.app.entities.Address;
import br.com.kirgh.app.enums.State;

/**
 * The {@code AddressMapper} class provides a static method to convert an {@code AddressDTO} object to an {@code Address}
 * object in Java.
 */
public abstract class AddressMapper {
    /**
     * The function converts an {@code AddressDTO} object to an {@code Address} object in Java.
     *
     * @param addressDTO {@code AddressDTO} is an object that represents an address in a data transfer object (DTO) format. It
     *                   contains the following properties: {@code street}, {@code zipCode}, {@code number}, {@code city}, and {@code state}.
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
     * The function takes an {@code AddressUpdateDTO} object and updates an existing {@code Address} object with the
     * values from the DTO.
     *
     * @param addressUpdateDTO An object of type {@code AddressUpdateDTO}, which contains the updated address
     *                         information.
     * @param updateAddress    The {@code updateAddress} parameter is an instance of the {@code Address} class that
     *                         needs to be updated with the values from the {@code AddressUpdateDTO} object.
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
