package br.com.kirgh.app.services;

import br.com.kirgh.app.dtos.UserDTO;
import br.com.kirgh.app.entities.User;
import br.com.kirgh.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@SuppressWarnings("unused")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Optional<User> createUser(UserDTO userDTO) {
        if (userRepository.existsById(userDTO.cpf())) {
            return Optional.empty();
        } else {
            return Optional.of(userRepository.save(new User(userDTO)));
        }
    }
}
