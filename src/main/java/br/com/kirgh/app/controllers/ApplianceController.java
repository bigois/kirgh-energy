package br.com.kirgh.app.controllers;

import br.com.kirgh.app.dtos.ApplianceDTO;
import br.com.kirgh.app.dtos.ApplianceUpdateDTO;
import br.com.kirgh.app.entities.Appliance;
import br.com.kirgh.app.services.ApplianceService;
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
 * The ApplianceController class is a Java REST controller that handles requests related to creating new appliances.
 */
@RestController
@RequestMapping(path = "/api/v1/appliances", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Appliances", description = "Set of API methods for appliance data manipulation")
@SuppressWarnings({"unused", "SpringJavaAutowiredFieldsWarningInspection"})
public class ApplianceController {
    @Autowired
    private ApplianceService applianceService;

    /**
     * This is a Java function that creates a new appliance using the data provided in the ApplianceDTO object.
     *
     * @param applianceDTO The parameter {@code applianceDTO} is of type {@code ApplianceDTO} and is annotated with {@code @RequestBody} and
     *                     {@code @Valid}. It represents the data transfer object that contains the information needed to create a new appliance. The
     *                     {@code @RequestBody} annotation indicates that the parameter should be populated with the
     * @return The method {@code applianceRegister} is returning a {@code ResponseEntity} object, which can contain any type of
     * response data along with an HTTP status code. The actual response data being returned depends on the implementation
     * of the {@code createAppliance} method in the {@code applianceService} class.
     */
    @Operation(
            summary = "Creates a new appliance to an existent address",
            description = "Method for creating a new appliance to an existent user address and returning a JSON response with the new appliance's ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = @Content(examples = {
                    @ExampleObject(summary = "Create an Appliance",
                            value = "{\"resourceId\": \"9fec9a47-db88-41b9-8cbd-d12c075e262f\",\"message\": \"appliance successfully registered\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),

            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid body content", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid content",
                            value = "{\"name\": \"invalid address id\", \"timestamp\": \"2023-08-26T01:00:05.809350200Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"message\": \"something goes wrong\", \"timestamp\": \"2023-08-26T00:21:30.426833300Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> applianceRegister(@RequestBody @Valid ApplianceDTO applianceDTO) {
        JSONObject response = new JSONObject();
        Appliance appliance = applianceService.createAppliance(applianceDTO);

        response.put("resourceId", appliance.getId());
        response.put("message", "appliance successfully registered");
        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }

    @Operation(
            summary = "Get a list of appliance's paginated",
            description = "Method for getting a list of paginated appliance to an existent and returning a JSON response with the appliance's info"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(examples = {
                    @ExampleObject(summary = "Get an list paginated of Appliance's",
                            value = "{\"content\": [{\"id\": \"a818055c-24db-43a4-a6fc-7f02808fe1bb\",\"name\": \"Ar Condicionado\",\"brand\": \"Samsung\",\"model\": \"AR12BVHZCWK\",\"power\": \"V110\"}],\"pageable\": {\"sort\": {\"empty\": false,\"sorted\": true,\"unsorted\": false},\"offset\": 0,\"pageNumber\": 0,\"pageSize\": 1,\"paged\": true,\"unpaged\": false},\"last\": false,\"totalPages\": 3,\"totalElements\": 3,\"size\": 1,\"number\": 0,\"sort\": {\"empty\": false,\"sorted\": true,\"unsorted\": false},\"first\": true,\"numberOfElements\": 1,\"empty\": false}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"message\": \"something goes wrong\", \"timestamp\": \"2023-08-26T00:21:30.426833300Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    // The above code is a Java method that handles a GET request. It retrieves a list of filtered
    // appliances based on the provided filters and pagination/sorting parameters.
    @GetMapping()
    public ResponseEntity<Page<Appliance>> getFilteredAppliances(
            @Parameter(description = "Pagination and sorting") Pageable pageable,
            @Parameter(description = "Filters for search") @RequestParam Map<String, String> filters) {
        Utils.removePageableKeysFromFilter(filters);

        Page<Appliance> appliances = applianceService.getFilteredAppliances(filters, pageable);
        return ResponseEntity.ok(appliances);
    }

    @Operation(
            summary = "Get a appliance by Id",
            description = "Method for getting a appliance and returning a JSON response with the appliance's info"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(examples = {
                    @ExampleObject(summary = "Get an Appliance by Id",
                            value = "{\"content\": [{\"id\": \"a818055c-24db-43a4-a6fc-7f02808fe1bb\",\"name\": \"Ar Condicionado\",\"brand\": \"Samsung\",\"model\": \"AR12BVHZCWK\",\"power\": \"V110\"}],\"pageable\": {\"sort\": {\"empty\": false,\"sorted\": true,\"unsorted\": false},\"offset\": 0,\"pageNumber\": 0,\"pageSize\": 1,\"paged\": true,\"unpaged\": false},\"last\": false,\"totalPages\": 3,\"totalElements\": 3,\"size\": 1,\"number\": 0,\"sort\": {\"empty\": false,\"sorted\": true,\"unsorted\": false},\"first\": true,\"numberOfElements\": 1,\"empty\": false}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),


            @ApiResponse(responseCode = "404", description = "NOT FOUND - Address id not found", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid Address Id",
                            value = "{\"message\": \"appliance not found\", \"timestamp\": \"2023-08-26T00:23:12.454577100Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"message\": \"something goes wrong\", \"timestamp\": \"2023-08-26T00:21:30.426833300Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    // The above code is a Java method that handles a GET request to retrieve information about an
    // appliance by its ID. It takes in a UUID parameter called "id" from the path, and calls the
    // "getAllApplianceInfoById" method from the "applianceService" object to retrieve the appliance
    // information. It then returns a ResponseEntity object with the appliance information in the response
    // body and a status code of 200 (OK).
    @GetMapping("/{id}")
    public ResponseEntity<Appliance> getAllApplianceInfoById(@PathVariable UUID id) {
        Appliance appliance = applianceService.getAllApplianceInfoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(appliance);
    }

    @Operation(
            summary = "Update appliance",
            description = "Method for updating a appliance and returning a JSON response with the new Appliance's info"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(examples = {
                    @ExampleObject(summary = "Create an Appliance",
                            value = "{\"id\": \"0415be05-b5e9-49b1-a51b-60ac820fb2e6\",\"name\": \"Air Cond\",\"brand\": \"LG\",\"model\": \"AR205\",\"power\": \"V110\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),

            @ApiResponse(responseCode = "404", description = "NOT FOUND - Address id not found", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid Address Id",
                            value = "{\"message\": \"appliance not found\", \"timestamp\": \"2023-08-26T00:23:12.454577100Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),

            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid body content", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid content",
                            value = "{\"power\": \"incorrect format power, must be V110/V220\",\"timestamp\": \"2023-08-26T02:03:39.942949Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"message\": \"something goes wrong\", \"timestamp\": \"2023-08-26T00:21:30.426833300Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    // The above code is a Java method that handles a PUT request to update the information of an appliance
    // identified by its ID.
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appliance> updateApplianceInfoById(@PathVariable UUID id, @RequestBody @Valid ApplianceUpdateDTO applianceUpdateDTO) {
        Appliance appliance = applianceService.updateApplianceInfoById(id, applianceUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(appliance);
    }

    @Operation(
            summary = "Delete appliance",
            description = "Method for deleting a appliance and returning a JSON response with no content"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content", content = @Content(examples = {
                    @ExampleObject(summary = "Create an Appliance",
                            value = "")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),

            @ApiResponse(responseCode = "404", description = "NOT FOUND - Appliance id not found", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid Address Id",
                            value = "{\"message\": \"appliance not found\", \"timestamp\": \"2023-08-26T00:23:12.454577100Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"message\": \"something goes wrong\", \"timestamp\": \"2023-08-26T00:21:30.426833300Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    // The above code is a Java method that handles a DELETE request to delete an appliance by its ID. It
    // takes in a UUID parameter representing the ID of the appliance to be deleted.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplianceById(@NonNull @PathVariable UUID id) {
        JSONObject response = new JSONObject();
        applianceService.deleteApplianceById(id);

        response.put("message", "address successfully deleted");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response.toString());
    }
}
