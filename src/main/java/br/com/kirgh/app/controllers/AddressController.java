package br.com.kirgh.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.MediaType;

import br.com.kirgh.app.dtos.AddressDTO;
import br.com.kirgh.app.services.AddressService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/addresses", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public ResponseEntity<?> addressRegister(@RequestBody @Valid AddressDTO addressDTO){
        ResponseEntity<?> address = addressService.createAddress(addressDTO);

        return address;
    }

}