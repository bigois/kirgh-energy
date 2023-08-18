package br.com.kirgh.app.controllers;

import br.com.kirgh.app.dtos.AddressCompleteInfoDTO;
import br.com.kirgh.app.dtos.AddressInfoDTO;
import br.com.kirgh.app.dtos.AddressDTO;
import br.com.kirgh.app.dtos.AddressUpdateDTO;
import br.com.kirgh.app.entities.Address;
import br.com.kirgh.app.entities.User;
import br.com.kirgh.app.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

/**
 * The AddressController class is a Java REST controller that handles POST requests to create a new address using an
 * AddressDTO object.
 */
@RestController
@RequestMapping(path = "/api/v1/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED - Address successfully registered", content = @Content(examples = {
                    @ExampleObject(summary = "Create an Address.",
                            value = "{\"street\": \"Rua Jose\", \"zipCode\":\"08421520\", \"number\": 185, \"city\":\"São Paulo\", \"state\":\"SP\", \"parentId\":\"6f007644-5bdf-4483-bf42-fb7412f66a45\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),

            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid body content", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid content",
                            value = "")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Parent id not found", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid Parent Id",
                            value = "{\"street\": \"Rua Jose\", \"zipCode\":\"08421520\", \"number\": 185, \"city\":\"São Paulo\", \"state\":\"SP\", \"parentId\":\"b1f6147e-4c1f-4362-930e-a77a7ee707fd\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "409", description = "CONFLICT - address already exists to user", content = @Content(examples = {
                    @ExampleObject(summary = "Conflict parent id",
                            value = "{\"street\": \"Rua Jose\", \"zipCode\":\"08421520\", \"number\": 185, \"city\":\"S\u00E3o Paulo\", \"state\":\"SP\", \"parentId\":\"6f007644-5bdf-4483-bf42-fb7412f66a45\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"street\": \"@\", \"zipCode\":\"@\", \"number\": \"@\", \"city\":\"@\", \"state\":\"@\", \"parentId\":\"@\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addressRegister(@RequestBody @Valid AddressDTO addressDTO) {
        JSONObject response = new JSONObject();
        Address address = addressService.createAddress(addressDTO);

        response.put("resourceId", address.getId());
        response.put("message", "address successfully registered");
        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }
    @GetMapping()
    public ResponseEntity<Page<Address>> getFilteredAppliances(
            @RequestParam Map<String, String> filters,
            Pageable pageable) {
        Page<Address> addresses = addressService.getFilteredAddresses(filters, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAllAddressInfoById(@PathVariable UUID id) {
        Address address = addressService.getAllAddressInfoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @GetMapping("/{id}/appliances")
    public ResponseEntity<AddressCompleteInfoDTO> getAllAppliancesBoundAddress(@PathVariable UUID id) {
        AddressCompleteInfoDTO addressCompleteInfoDTO = addressService.getAllAppliancesBoundAddress(id);
        return ResponseEntity.status(HttpStatus.OK).body(addressCompleteInfoDTO);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> updateAddressInfoById(@PathVariable UUID id, @RequestBody @Valid AddressUpdateDTO addressUpdateDto) {
        Address address = addressService.updateAddressInfoById(id, addressUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddressById(@NonNull @PathVariable UUID id) {
        JSONObject response = new JSONObject();
        addressService.deleteAddressById(id);

        response.put("message", "address successfully deleted");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response.toString());
    }
}
