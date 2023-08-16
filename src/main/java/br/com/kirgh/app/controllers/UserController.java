package br.com.kirgh.app.controllers;

import br.com.kirgh.app.dtos.UserCompDTO;
import br.com.kirgh.app.dtos.UserCompleteDTO;
import br.com.kirgh.app.dtos.UserDTO;
import br.com.kirgh.app.dtos.UserUpdateDTO;
import br.com.kirgh.app.entities.User;
import br.com.kirgh.app.services.UserService;
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
 * The UserController class defines a REST API endpoint for creating a new user by taking in a validated userDTO object and
 * returning a ResponseEntity.
 */
@RestController
@RequestMapping(path = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Set of API methods for user data manipulation")
@SuppressWarnings({"unused", "SpringJavaAutowiredFieldsWarningInspection"})
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * This is a Java function that creates a new user by taking in a validated userDTO object and returns a
     * ResponseEntity.
     *
     * @param userDTO userDTO is an object of the class UserDTO which is annotated with @Valid to indicate that the object
     *                should be validated before processing. It is passed as a request body in a POST request to the userRegister()
     *                method. The method then calls the createUser() method of the userService object and returns the
     * @return The method {@code userRegister} is returning a {@code ResponseEntity} object, which can contain any type of response
     * data along with an HTTP status code. The actual response data being returned depends on the implementation of the
     * {@code createUser} method in the {@code userService} object.
     */

    @Operation(
            summary = "Creates a new user (with or without parent relation)",
            description = "Method for creating a new user with an optional user parent relation and returning a JSON response with the new user's ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED - Address successfully registered", content = @Content(examples = {
                    @ExampleObject(summary = "Create an User.",
                            value = "{\"name\": \"Renata Luzia Francisca Porto\", \"birthDate\": \"2002-06-30\", \"gender\": \"F\", \"cpf\": \"29081928619\", \"email\": \"renataluziaporto@asconinternet.com.br\", \"relation\": { \"ownerId\": \"67459848-3af5-4c99-9276-543c331adcc1\", \"relaionType\": \"Daughter\"} }")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),

            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid body content", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid content",
                            value = "")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Owner id not found", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid Parent Id",
                            value = "{\"name\": \"Renata Luzia Francisca Porto\", \"birthDate\": \"2002-06-30\", \"gender\": \"F\", \"cpf\": \"29081928619\", \"email\": \"renataluziaporto@asconinternet.com.br\", \"relation\": { \"ownerId\": \"b1f6147e-4c1f-4362-930e-a77a7ee707fd\", \"relaionType\": \"Daughter\"} }")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "409", description = "CONFLICT - address already exists to user", content = @Content(examples = {
                    @ExampleObject(summary = "Conflict user id",
                            value = "{\"name\": \"Renata Luzia Francisca Porto\", \"birthDate\": \"2002-06-30\", \"gender\": \"F\", \"cpf\": \"29081928619\", \"email\": \"renataluziaporto@asconinternet.com.br\", \"relation\": { \"ownerId\": \"67459848-3af5-4c99-9276-543c331adcc1\", \"relaionType\": \"Daughter\"} }")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"name\": \"@\", \"birthDate\": \"@\", \"gender\": \"@\", \"cpf\": \"@\", \"email\": \"@\", \"relation\": { \"ownerId\": \"@\", \"relaionType\": \"@\"} }")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> userRegister(@RequestBody @Valid UserDTO userDTO) {
        JSONObject response = new JSONObject();
        User user = userService.createUser(userDTO);

        response.put("resourceId", user.getId());
        response.put("message", "user successfully registered");
        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserCompleteDTO> getAllUserInfoById(@PathVariable UUID id) {
        UserCompleteDTO userCompleteDTO = userService.getAllUserInfoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userCompleteDTO);
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<UserCompDTO> getAllAddressesBoundUser(@PathVariable UUID id) {
        UserCompDTO userCompDTO = userService.getAllAddressesBoundUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userCompDTO);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUserInfoById(@PathVariable UUID id, @RequestBody UserUpdateDTO userUpdateDTO) {
        User user = userService.updateUserInfoById(id, userUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@NonNull @PathVariable UUID id) {
        JSONObject response = new JSONObject();
        userService.deleteUserById(id);

        response.put("message", "user successfully deleted");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response.toString());
    }
}
