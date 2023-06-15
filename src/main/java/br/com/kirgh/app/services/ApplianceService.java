package br.com.kirgh.app.services;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.kirgh.app.dtos.ApplianceDTO;
import br.com.kirgh.app.entities.Appliance;
import br.com.kirgh.app.entities.ApplianceRelation;
import br.com.kirgh.app.mapper.ApplianceMapper;
import br.com.kirgh.app.repositories.AddressRepository;
import br.com.kirgh.app.repositories.ApplianceRelationRepository;
import br.com.kirgh.app.repositories.ApplianceRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ApplianceService {

    @Autowired
    private ApplianceRepository applianceRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ApplianceRelationRepository applianceRelationRepository;

    public ResponseEntity<?> createAppliance(ApplianceDTO applianceDTO){
        JSONObject response = new JSONObject();

        if (!addressRepository.existsById(applianceDTO.addressId())) {
            throw new EntityNotFoundException("address id not found");
        }
        
        Appliance appliance = applianceRepository.save(ApplianceMapper.applianceDTOToAppliance(applianceDTO));

        ApplianceRelation applianceRelation = new ApplianceRelation();

        applianceRelation.getApplianceRelationPK().setAppliance(appliance);
        applianceRelation.getApplianceRelationPK().setAddress(addressRepository.findById(applianceDTO.addressId()).orElse(null));

        applianceRelationRepository.save(applianceRelation);

        response.put("message", "appliance successfully registered");
        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }
}
