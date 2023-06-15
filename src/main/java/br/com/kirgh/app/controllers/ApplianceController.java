package br.com.kirgh.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kirgh.app.dtos.ApplianceDTO;
import br.com.kirgh.app.services.ApplianceService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/appliances", produces = MediaType.APPLICATION_JSON_VALUE)

public class ApplianceController {

    @Autowired
    private ApplianceService applianceService;

    @PostMapping
    public ResponseEntity<?> applianceRegister(@RequestBody @Valid ApplianceDTO applianceDTO){
        ResponseEntity<?> appliance = applianceService.createAppliance(applianceDTO);

        return appliance;
    }
}
