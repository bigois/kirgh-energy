package br.com.kirgh.app.controllers;

import br.com.kirgh.app.dtos.UserCompleteInfoDTO;
import br.com.kirgh.app.dtos.UserDTO;
import br.com.kirgh.app.dtos.UserUpdateDTO;
import br.com.kirgh.app.entities.User;
import br.com.kirgh.app.services.UserService;
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
 * The {@code UserController} class defines a REST API endpoint for creating a new user by taking in a validated {@code userDTO} object and
 * returning a {@code ResponseEntity}.
 */
@RestController
@RequestMapping(path = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Set of API methods for user data manipulation")
@SuppressWarnings({"unused", "SpringJavaAutowiredFieldsWarningInspection"})
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * This is a Java function that creates a new user by taking in a validated {@code userDTO} object and returns a
     * {@code ResponseEntity}.
     *
     * @param userDTO {@code userDTO} is an object of the class {@code UserDTO} which is annotated with {@code @Valid} to indicate that the object
     *                should be validated before processing. It is passed as a request body in a POST request to the {@code userRegister()}
     *                method. The method then calls the {@code createUser()} method of the {@code userService} object and returns the
     * @return The method {@code userRegister} is returning a {@code ResponseEntity} object, which can contain any type of response
     * data along with an HTTP status code. The actual response data being returned depends on the implementation of the
     * {@code createUser} method in the {@code userService} object.
     */
    @Operation(
            summary = "Creates a new user (with or without parent relation)",
            description = "Method for creating a new user with an optional user parent relation and returning a JSON response with the new user's ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED - User successfully registered", content = @Content(examples = {
                    @ExampleObject(summary = "Create an User.",
                            value = "{\"name\": \"Renata Luzia Francisca Porto\", \"birthDate\": \"2002-06-30\", \"gender\": \"F\", \"cpf\": \"29081928619\", \"email\": \"renataluziaporto@asconinternet.com.br\", \"relation\": { \"ownerId\": \"67459848-3af5-4c99-9276-543c331adcc1\", \"relaionType\": \"Daughter\"} }")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),

            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid body content", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid content",
                            value = "{\"name\": \"must contain only letters\", \"timestamp\": \"2023-08-26T01:00:05.809350200Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Owner id not found", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid Parent Id",
                            value = "{\"name\": \"Renata Luzia Francisca Porto\", \"birthDate\": \"2002-06-30\", \"gender\": \"F\", \"cpf\": \"29081928619\", \"email\": \"renataluziaporto@asconinternet.com.br\", \"relation\": { \"ownerId\": \"b1f6147e-4c1f-4362-930e-a77a7ee707fd\", \"relaionType\": \"Daughter\"} }")
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
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> userRegister(@RequestBody @Valid UserDTO userDTO) {
        JSONObject response = new JSONObject();
        User user = userService.createUser(userDTO);

        response.put("resourceId", user.getId());
        response.put("message", "user successfully registered");
        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }

    /**
     * This function retrieves a paginated list of users based on specified filters and returns it as a
     * JSON response.
     *
     * @param pageable The {@code pageable} parameter is used for pagination and sorting. It allows you to
     *                 specify the page number, page size, and sorting criteria for the returned list of users.
     * @param filters  A map containing the filters for searching. The keys in the map represent the filter
     *                 criteria, and the values represent the filter values.
     * @return The method is returning a {@code ResponseEntity} object with a JSON response body containing a
     * {@code Page<User>} object.
     */
    @Operation(
            summary = "Get user's paginated",
            description = "Method to get a list of user's paginated with JSON response with the user's info"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(examples = {
                    @ExampleObject(summary = "Get an User.",
                            value = "{\"content\": [{\"id\": \"85b9a0b6-6cb1-4f18-8d95-f0a0bc99c20a\",\"cpf\": \"14358166415\",\"name\": \"Liz Cristiane Alves\",\"email\": \"lizcristianealves@alvesbarcelos.com.br\",\"birthDate\": \"1943-03-21T03:00:00.000+00:00\",\"gender\": \"F\"}],\"pageable\": {\"sort\": {\"empty\": false,\"sorted\": true,\"unsorted\": false},\"offset\": 0,\"pageNumber\": 0,\"pageSize\": 1,\"unpaged\": false,\"paged\": true},\"last\": false,\"totalPages\": 15,\"totalElements\": 15,\"size\": 1,\"number\": 0,\"sort\": {\"empty\": false,\"sorted\": true,\"unsorted\": false},\"first\": true,\"numberOfElements\": 1,\"empty\": false}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"message\": \"something goes wrong\", \"timestamp\": \"2023-08-26T00:21:30.426833300Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping
    public ResponseEntity<Page<User>> getFilteredUsers(
            @Parameter(description = "Pagination and sorting") Pageable pageable,
            @Parameter(description = "Filters for search") @RequestParam Map<String, String> filters) {
        Utils.removePageableKeysFromFilter(filters);

        Page<User> users = userService.getFilteredUsers(filters, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    /**
     * This function retrieves user information by their ID and returns a JSON response with the user's
     * info.
     *
     * @param id The {@code id} parameter is a UUID (Universally Unique Identifier) that represents the unique
     *           identifier of a user.
     * @return The method is returning a {@code ResponseEntity} object with the HTTP status code 200 (OK) and the
     * user's information in the response body.
     */
    @Operation(
            summary = "Get user information by id ",
            description = "Method to get a user by id returning a JSON response with the user's info"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(examples = {
                    @ExampleObject(summary = "Get an User.",
                            value = "{\"id\": \"6f007644-5bdf-4483-bf42-fb7412f66a45\", \"cpf\": \"29081928619\", \"name\": \"Renata Luzia Francisca Porto\", \"email\": \"renataluziaporto@asconinternet.com.br\", \"birthDate\": \"1957-04-08T03:00:00.000+00:00\", \"gender\": \"F\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - User id not found", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid Parent Id",
                            value = "{\"message\": \"user not found\", \"timestamp\": \"2023-08-26T00:23:12.454577100Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"message\": \"something goes wrong\", \"timestamp\": \"2023-08-26T00:21:30.426833300Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getAllUserInfoById(@PathVariable UUID id) {
        User user = userService.getAllUserInfoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    /**
     * This function retrieves all addresses associated with a user by their ID.
     *
     * @param id The id parameter is a UUID (Universally Unique Identifier) that represents the unique
     *           identifier of a user.
     * @return The method is returning a {@code ResponseEntity} object with the status code 200 (OK) and the body
     * containing a {@code UserCompleteInfoDTO} object.
     */
    @Operation(
            summary = "Get user by id (with or without parent relation)",
            description = "Method to get a user by id returning a JSON response with the user's and addresses info"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(examples = {
                    @ExampleObject(summary = "Get an User.",
                            value = "{\"userData\": {\"id\": \"ab8ea442-d9bb-466d-8d47-3853091d545d\",\"cpf\": \"25276887560\",\"name\": \"Caroline Larissa Assunção\",\"email\": \"caroline_larissa_assuncao@infonet.com.br\",\"birthDate\": \"1983-02-09T03:00:00.000+00:00\",\"gender\": \"F\"},\"userRelation\": [],\"addresses\": [{\"addressData\": {\"id\": \"4f509dc1-3517-4dd3-b7d6-8bd21fc9e732\",\"zipCode\": \"08423555\",\"street\": \"Rua dsa\",\"number\": \"555\",\"city\": \"Sao Paulo\",\"state\": \"SP\"},\"appliances\": [{\"id\": \"4ab28223-a87d-46bd-a55d-164604450454\",\"name\": \"Air conditioning\",\"brand\": \"Samsung\",\"model\": \"AR415\",\"power\": \"V220\"}]}]}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - User id not found", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid Parent Id",
                            value = "{\"message\": \"user not found\", \"timestamp\": \"2023-08-26T00:23:12.454577100Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR - Something goes wrong", content = @Content(examples = {
                    @ExampleObject(summary = "Internal Server Error",
                            value = "{\"message\": \"something goes wrong\", \"timestamp\": \"2023-08-26T00:21:30.426833300Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
    @GetMapping("/{id}/addresses")
    public ResponseEntity<UserCompleteInfoDTO> getAllAddressesBoundUser(@PathVariable UUID id) {
        UserCompleteInfoDTO userCompleteInfoDTO = userService.getAllAddressesBoundUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userCompleteInfoDTO);
    }

    /**
     * This function updates a user's information and returns a JSON response with the new user's info.
     *
     * @param id            The id parameter is a UUID (Universally Unique Identifier) that represents the unique
     *                      identifier of the user to be updated.
     * @param userUpdateDTO The {@code userUpdateDTO} is a data transfer object that contains the updated
     *                      information for the user. It is annotated with {@code @Valid} to perform validation on the request body. The
     *                      fields in the {@code userUpdateDTO} include:
     * @return The method is returning a {@code ResponseEntity} object with the updated User object in the body.
     * The HTTP status code of the response is 200 (OK).
     */
    @Operation(
            summary = "Update user (with or without parent relation)",
            description = "Method for updating a user and returning a JSON response with the new user's Info"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - User successfully updated", content = @Content(examples = {
                    @ExampleObject(summary = "Update an User.",
                            value = "{\"name\": \"Renata Luzia Francisca Porto\", \"birthDate\": \"2002-06-30\", \"gender\": \"F\", \"cpf\": \"29081928619\", \"email\": \"renataluziaporto@asconinternet.com.br\", \"relation\": { \"ownerId\": \"67459848-3af5-4c99-9276-543c331adcc1\", \"relaionType\": \"Daughter\"} }")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),

            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid body content", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid content",
                            value = "{\"name\": \"must contain only letters\", \"timestamp\": \"2023-08-26T01:00:05.809350200Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Owner id not found", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid Parent Id",
                            value = "{\"message\": \"user not found\", \"timestamp\": \"2023-08-26T00:23:12.454577100Z\"}")
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
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUserInfoById(@PathVariable UUID id, @RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        User user = userService.updateUserInfoById(id, userUpdateDTO);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    /**
     * This function is used to delete a user by their ID and return a JSON response with a success
     * message.
     *
     * @param id The {@code id} parameter is a UUID (Universally Unique Identifier) that represents the unique
     *           identifier of the user to be deleted.
     * @return The method is returning a {@code ResponseEntity<String>} object.
     */
    @Operation(
            summary = "Delete user (with or without parent relation)",
            description = "Method for delete a user and returning a JSON response with no content"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "OK - User successfully deleted", content = @Content(examples = {
                    @ExampleObject(summary = "Delete an User.")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid body content", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid content",
                            value = "{\"name\": \"must contain only letters\", \"timestamp\": \"2023-08-26T01:00:05.809350200Z\"}")
            }, mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - Owner id not found", content = @Content(examples = {
                    @ExampleObject(summary = "Invalid Parent Id",
                            value = "{\"message\": \"user not found\", \"timestamp\": \"2023-08-26T00:23:12.454577100Z\"}")
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
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@NonNull @PathVariable UUID id) {
        JSONObject response = new JSONObject();
        userService.deleteUserById(id);

        response.put("message", "user successfully deleted");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response.toString());
    }
}
