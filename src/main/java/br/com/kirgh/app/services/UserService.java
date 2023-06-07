package br.com.kirgh.app.services;

import br.com.kirgh.app.dtos.UserDTO;
import br.com.kirgh.app.entities.User;
import br.com.kirgh.app.entities.UserRelation;
import br.com.kirgh.app.mapper.UserMapper;
import br.com.kirgh.app.repositories.UserRelationRepository;
import br.com.kirgh.app.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("unused")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRelationRepository userRelationRepository;

    @Transactional
    public ResponseEntity<?> createUser(UserDTO userDTO) {
        JSONObject response = new JSONObject();

        if (userRepository.existsById(userDTO.cpf()) || userRepository.existsByEmail(userDTO.email())) {
            throw new IllegalArgumentException("user already exists");
        }

        if (userDTO.relation() != null && !userRepository.existsById(userDTO.relation().parentId())) {
            throw new EntityNotFoundException("owner id not found");
        }

        User user = userRepository.save(UserMapper.userDTOToUser(userDTO));

        if (userDTO.relation() != null) {
            UserRelation userRelation = new UserRelation();

            userRelation.setRelationType(userDTO.relation().relationType());
            userRelation.getUserRelationPK().setChild(user);
            userRelation.getUserRelationPK().setOwner(userRepository.findById(userDTO.relation().parentId()).orElse(null));

            userRelationRepository.save(userRelation);
        }

        response.put("message", "user successfully registered");
        return ResponseEntity.status(HttpStatus.CREATED).body(response.toString());
    }
}
