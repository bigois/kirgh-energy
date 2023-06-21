package br.com.kirgh.app.controllers;

import br.com.kirgh.app.dtos.AddressDTO;
import br.com.kirgh.app.entities.Address;
import br.com.kirgh.app.services.AddressService;
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
@SuppressWarnings("unused")
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * This is a Java function that creates a new address using the provided AddressDTO object.
     *
     * @param addressDTO AddressDTO is a parameter of type AddressDTO that is annotated with @RequestBody and @Valid. It is
     *                   used to receive the request body of a POST request that contains information about a new address to be registered.
     *                   The @Valid annotation is used to validate the input data against any constraints specified in the Address
     * @return The method {@code addressRegister} is returning a {@code ResponseEntity} object, which can contain any type of response
     * data along with an HTTP status code. The specific response data being returned depends on the implementation of the
     * {@code createAddress} method in the {@code addressService} object.
     */
    @PostMapping
    public ResponseEntity<String> addressRegister(@RequestBody @Valid AddressDTO addressDTO) {
        JSONObject response = new JSONObject();
        Address address = addressService.createAddress(addressDTO);

        response.put("resourceId", address.getId());
        response.put("message", "address successfully registered");
        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }
}
