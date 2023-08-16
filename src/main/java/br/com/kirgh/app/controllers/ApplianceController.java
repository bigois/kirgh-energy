package br.com.kirgh.app.controllers;

import br.com.kirgh.app.dtos.ApplianceInfoDTO;
import br.com.kirgh.app.dtos.ApplianceDTO;
import br.com.kirgh.app.dtos.ApplianceUpdateDTO;
import br.com.kirgh.app.entities.Appliance;
import br.com.kirgh.app.services.ApplianceService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @ApiResponse(responseCode = "201", description = "CREATED - Appliance successfully registered", content = @Content(examples = {
                    @ExampleObject(summary = "Create an Appliance",
                            value = "{\"name\": \"Ar Condicionado\", \"brand\":\"Samsung\", \"model\": \"AR12BVHZCWK\", \"power\":\"V110\", \"addressId\":\"26ead1cd-c0d6-47bd-bb79-f0aeb4b897bb\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),

            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid body content", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid content",
                            value = "")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Address id not found", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid Address Id",
                            value = "{\"name\": \"Ar Condicionado\", \"brand\":\"Samsung\", \"model\": \"AR12BVHZCWK\", \"power\":\"V110\", \"addressId\":\"26ead1cd-c0d6-47bd-bb79-f1aeb4b897bb\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"name\": \"@\", \"brand\":\"@\", \"model\": \"@\", \"power\":\"@\", \"addressId\":\"@\"}")
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

    @GetMapping("/{id}")
    public ResponseEntity<Appliance> getAllUserInfoById(@PathVariable UUID id) {
        Appliance appliance = applianceService.getAllApplianceInfoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(appliance);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Appliance> updateApplianceInfoById(@PathVariable UUID id, @RequestBody @Valid ApplianceUpdateDTO applianceUpdateDTO) {
        Appliance appliance = applianceService.updateApplianceInfoById(id, applianceUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(appliance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteApplianceById(@NonNull @PathVariable UUID id) {
        JSONObject response = new JSONObject();
        applianceService.deleteApplianceById(id);

        response.put("message", "address successfully deleted");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response.toString());
    }
}
