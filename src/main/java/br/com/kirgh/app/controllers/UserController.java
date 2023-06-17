package br.com.kirgh.app.controllers;

import br.com.kirgh.app.dtos.UserDTO;
import br.com.kirgh.app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The UserController class defines a REST API endpoint for creating a new user by taking in a validated userDTO object and
 * returning a ResponseEntity.
 */
@RestController
@RequestMapping(path = "/api/v1/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@SuppressWarnings("unused")
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
    @PostMapping
    public ResponseEntity<?> userRegister(@RequestBody @Valid UserDTO userDTO) {
        return userService.createUser(userDTO);
    }
}
