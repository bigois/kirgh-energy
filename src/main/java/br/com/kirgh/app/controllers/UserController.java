package br.com.kirgh.app.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import br.com.kirgh.app.dtos.UserDTO;
import br.com.kirgh.app.entities.User;
import br.com.kirgh.app.services.UserService;

@RestController
@RequestMapping(path = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity userRegister(@RequestBody @Valid UserDTO userDTO){
        User user = userService.createUser(userDTO);
        JSONObject response = new JSONObject();

        if (user == null) {
            response.put("message", "user already registered");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.toString());
        } else {
            response.put("message", "user successfully registered");
            return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
        }
    }
}
