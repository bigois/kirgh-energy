package br.com.kirgh.app.controllers;

import br.com.kirgh.app.dtos.AddressDTO;
import br.com.kirgh.app.entities.Address;
import br.com.kirgh.app.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The AddressController class is a Java REST controller that handles POST requests to create a new address using an
 * AddressDTO object.
 */
@RestController
@RequestMapping(path = "/api/v1/addresses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Addresses", description = "Set of API methods for address data manipulation")
@SuppressWarnings({"unused", "SpringJavaAutowiredFieldsWarningInspection"})
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * This function registers a new address by creating an Address object from the provided AddressDTO and returning a
     * JSON response with the new address's ID and a success message.
     *
     * @param addressDTO AddressDTO is an object that contains the details of an address such as street, city, state, and
     *                   zip code. It is annotated with @Valid to ensure that the input data is valid and meets the required constraints. The
     * @return A ResponseEntity object containing a JSON response with the resourceId and message of the newly created
     * Address object. The HTTP status code of the response is set to 201 (CREATED).
     */
    @Operation(
        summary = "Creates a new address to an existent user",
        description = "Method for creating a new address to an existent user and returning a JSON response with the new address's ID"
    )
    @PostMapping
    public ResponseEntity<String> addressRegister(@RequestBody @Valid AddressDTO addressDTO) {
        JSONObject response = new JSONObject();
        Address address = addressService.createAddress(addressDTO);

        response.put("resourceId", address.getId());
        response.put("message", "address successfully registered");
        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }
}
