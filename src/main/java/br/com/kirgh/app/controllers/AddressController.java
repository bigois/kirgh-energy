package br.com.kirgh.app.controllers;

import br.com.kirgh.app.dtos.AddressCompleteInfoDTO;
import br.com.kirgh.app.dtos.AddressDTO;
import br.com.kirgh.app.dtos.AddressUpdateDTO;
import br.com.kirgh.app.entities.Address;
import br.com.kirgh.app.entities.User;
import br.com.kirgh.app.services.AddressService;
import br.com.kirgh.app.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
                            value = "{\"model\": \"cannot be null or empty\",\"timestamp\": \"2023-08-26T01:51:40.016469Z\"}")
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
    // The above code is a Java method that handles a POST request for registering an address. It takes in
    // a JSON object representing an address as the request body. The address is validated using the @Valid
    // annotation.
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addressRegister(@RequestBody @Valid AddressDTO addressDTO) {
        JSONObject response = new JSONObject();
        Address address = addressService.createAddress(addressDTO);

        response.put("resourceId", address.getId());
        response.put("message", "address successfully registered");
        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }

    @Operation(
            summary = "Get addresses paginated ",
            description = "Method to get a list of addresses paginated with JSON response with the address info"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(examples = {
                    @ExampleObject(summary = "Get an list of paginated Addresses.",
                            value = "{\"content\": [{\"id\": \"26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb\",\"zipCode\": \"03701010\",\"street\": \"Avenida Gabriela Mistral\",\"number\": \"670\",\"city\": \"São Paulo\",\"state\": \"SP\"}],\"pageable\": {\"sort\": {\"empty\": false,\"sorted\": true,\"unsorted\": false},\"offset\": 0,\"pageNumber\": 0,\"pageSize\": 1,\"paged\": true,\"unpaged\": false},\"last\": false,\"totalPages\": 3,\"totalElements\": 3,\"size\": 1,\"number\": 0,\"sort\": {\"empty\": false,\"sorted\": true,\"unsorted\": false},\"first\": true,\"numberOfElements\": 1,\"empty\": false}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"message\": \"something goes wrong\", \"timestamp\": \"2023-08-26T00:21:30.426833300Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    // The above code is a Java method that handles a GET request. It retrieves a list of filtered
    // addresses based on the provided filters and pagination/sorting parameters.
    @GetMapping()
    public ResponseEntity<Page<Address>> getFilteredAddresses(
            @Parameter(description = "Pagination and sorting") Pageable pageable,
            @Parameter(description = "Filters for search") @RequestParam Map<String, String> filters) {
        Utils.removePageableKeysFromFilter(filters);

        Page<Address> addresses = addressService.getFilteredAddresses(filters, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    @Operation(
            summary = "Get address information by id ",
            description = "Method to get a address by id returning a JSON response with the address info"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(examples = {
                    @ExampleObject(summary = "Get an Address.",
                            value = "{\"id\": \"26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb\",\"zipCode\": \"03701010\",\"street\": \"Avenida Gabriela Mistral\",\"number\": \"670\",\"city\": \"São Paulo\",\"state\": \"SP\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - address id not found", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid Address Id",
                            value = "{\"message\": \"address not found\", \"timestamp\": \"2023-08-26T00:23:12.454577100Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"message\": \"something goes wrong\", \"timestamp\": \"2023-08-26T00:21:30.426833300Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    // The above code is a Java method that handles a GET request to retrieve address information by its
    // ID. It takes in a UUID parameter called "id" from the path, and calls a method called
    // "getAllAddressInfoById" from the "addressService" object to retrieve the address information. It
    // then returns a ResponseEntity object with a status of OK (200) and the retrieved address information
    // in the response body.
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAllAddressInfoById(@PathVariable UUID id) {
        Address address = addressService.getAllAddressInfoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @Operation(
            summary = "Get address information by id (with or without appliances) ",
            description = "Method to get a address by id returning a JSON response with the address info"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(examples = {
                    @ExampleObject(summary = "Get an Address.",
                            value = "{\"addressData\": {\"id\": \"26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb\",\"zipCode\": \"03701010\",\"street\": \"Avenida Gabriela Mistral\",\"number\": \"670\",\"city\": \"São Paulo\",\"state\": \"SP\"},\"appliances\": [{\"id\": \"0415be05-b5e9-49b1-a51b-60ac820fb2e6\",\"name\": \"Fogão\",\"brand\": \"Atlas\",\"model\": \"Mônaco\",\"power\": \"V110\"}]}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - address id not found", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid Address Id",
                            value = "{\"message\": \"address not found\", \"timestamp\": \"2023-08-26T00:23:12.454577100Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"message\": \"something goes wrong\", \"timestamp\": \"2023-08-26T00:21:30.426833300Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    // The above code is a Java method that handles a GET request to retrieve all appliances bound to a
    // specific address. It takes a UUID parameter "id" representing the address ID. It calls the
    // "getAllAppliancesBoundAddress" method from the "addressService" to retrieve the complete information
    // of the address along with the appliances bound to it. It then returns a ResponseEntity object with
    // the HTTP status set to OK (200) and the body containing the addressCompleteInfoDTO object.
    @GetMapping("/{id}/appliances")
    public ResponseEntity<AddressCompleteInfoDTO> getAllAppliancesBoundAddress(@PathVariable UUID id) {
        AddressCompleteInfoDTO addressCompleteInfoDTO = addressService.getAllAppliancesBoundAddress(id);
        return ResponseEntity.status(HttpStatus.OK).body(addressCompleteInfoDTO);
    }

    @Operation(
            summary = "Update address (with or without parent relation)",
            description = "Method for updating a address and returning a JSON response with the new user's Address info"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(examples = {
                    @ExampleObject(summary = "Update an Address.",
                            value = "{\"id\": \"26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb\",\"zipCode\": \"12332112\",\"street\": \"Rual Abril\",\"number\": \"70\",\"city\": \"São Paulo\",\"state\": \"SP\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),

            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid body content", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid content",
                            value = "{\"name\": \"must contain only letters\", \"timestamp\": \"2023-08-26T01:00:05.809350200Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Owner id not found", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid Address Id",
                            value = "{\"message\": \"address not found\", \"timestamp\": \"2023-08-26T00:23:12.454577100Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "409", description = "CONFLICT - address already exists to user", content = @Content(examples = {
                    @ExampleObject(summary = "Conflict user id",
                            value = "{\"message\": \"at least one attribute needs to be valid\", \"timestamp\": \"2023-08-26T00:58:41.439908900Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"message\": \"something goes wrong\", \"timestamp\": \"2023-08-26T00:21:30.426833300Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    // The above code is a Java method that handles a PUT request to update an address by its ID. It takes
    // in the ID as a path variable and the updated address information as a JSON request body. The method
    // then calls the addressService to update the address information and returns a ResponseEntity with
    // the updated address and an HTTP status code of 200 (OK).
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Address> updateAddressInfoById(@PathVariable UUID id, @RequestBody @Valid AddressUpdateDTO addressUpdateDto) {
        Address address = addressService.updateAddressInfoById(id, addressUpdateDto);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @Operation(
            summary = "Delete address (with or without parent relation)",
            description = "Method for delete a address and returning a JSON response with no content"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(examples = {
                    @ExampleObject(summary = "Delete an Address.",
                            value = "")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),

            @ApiResponse(responseCode = "404", description = "NOT FOUND - Owner id not found", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid Address Id",
                            value = "{\"message\": \"address not found\", \"timestamp\": \"2023-08-26T00:23:12.454577100Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "409", description = "CONFLICT - address already exists to user", content = @Content(examples = {
                    @ExampleObject(summary = "Conflict user id",
                            value = "{\"message\": \"at least one attribute needs to be valid\", \"timestamp\": \"2023-08-26T00:58:41.439908900Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"message\": \"something goes wrong\", \"timestamp\": \"2023-08-26T00:21:30.426833300Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    // The above code is a Java method that handles a DELETE request to delete an address by its ID. It
    // takes in a UUID parameter representing the ID of the address to be deleted.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddressById(@NonNull @PathVariable UUID id) {
        JSONObject response = new JSONObject();
        addressService.deleteAddressById(id);

        response.put("message", "address successfully deleted");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response.toString());
    }
}
