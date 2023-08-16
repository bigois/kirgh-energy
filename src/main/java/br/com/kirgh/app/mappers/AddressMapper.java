package br.com.kirgh.app.mappers;

import br.com.kirgh.app.dtos.AddressCompleteDTO;
import br.com.kirgh.app.dtos.AddressDTO;
import br.com.kirgh.app.dtos.AddressUpdateDTO;
import br.com.kirgh.app.entities.Address;
import br.com.kirgh.app.projections.AddressProjection;
import br.com.kirgh.app.utils.Utils;

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

    public static Address addressUpdateDTOToAddress(AddressUpdateDTO addressUpdateDTO, Address updateAddress) {
        updateAddress.setStreet(addressUpdateDTO.street());
        updateAddress.setNumber(addressUpdateDTO.number());
        updateAddress.setZipCode(addressUpdateDTO.zipCode());
        updateAddress.setCity(addressUpdateDTO.city());
        updateAddress.setState(addressUpdateDTO.state());
        return updateAddress;
    }

    public static AddressCompleteDTO addressCompleteProjectionToAddressCompleteDTO(AddressProjection addressProjection) {
        return new AddressCompleteDTO(Utils.convertBytesToUUID(addressProjection.getId()),
                addressProjection.getZipCode(), addressProjection.getStreet(), addressProjection.getNumber(), addressProjection.getCity(), addressProjection.getState());
    }
}
