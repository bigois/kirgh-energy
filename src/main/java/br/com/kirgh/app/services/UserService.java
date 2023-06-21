package br.com.kirgh.app.services;

import br.com.kirgh.app.dtos.UserDTO;
import br.com.kirgh.app.entities.User;
import br.com.kirgh.app.entities.UserRelation;
import br.com.kirgh.app.mappers.UserMapper;
import br.com.kirgh.app.repositories.UserRelationRepository;
import br.com.kirgh.app.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * The UserService class creates a new user and saves it to the database, along with a user relation if specified, and
 * returns a success message.
 */
@Service
@SuppressWarnings("unused")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRelationRepository userRelationRepository;

    /**
     * This Java function creates a new user and saves it to the database, along with a user relation if specified, and
     * returns a success message.
     *
     * @param userDTO UserDTO is an object that contains the information of a user, such as their name, email, CPF
     *                (Brazilian individual taxpayer registry number), and information about their relationship with another user (if
     *                applicable).
     * @return A ResponseEntity object with a JSON response body containing a message indicating that the user was
     * successfully registered, and an HTTP status code of 201 (CREATED).
     */
    @Transactional
    public User createUser(UserDTO userDTO) {
        if (userRepository.existsByCpf(userDTO.cpf()) || userRepository.existsByEmail(userDTO.email())) {
            throw new IllegalArgumentException("user already exists");
        }

        if (userDTO.relation() != null && !userRepository.existsById(UUID.fromString(userDTO.relation().ownerId()))) {
            throw new EntityNotFoundException("owner id not found");
        }

        User user = userRepository.save(UserMapper.userDTOToUser(userDTO));

        if (userDTO.relation() != null) {
            UserRelation userRelation = new UserRelation();
            userRelation.setRelationType(userDTO.relation().relationType());
            userRelation.getUserRelationPK().setChild(user);
            userRelation.getUserRelationPK().setOwner(userRepository.findById(UUID.fromString(userDTO.relation().ownerId())).orElseThrow(() -> new EntityNotFoundException()));
            userRelationRepository.save(userRelation);
        }

        return user;
    }
}
