package br.com.kirgh.app.services;

import br.com.kirgh.app.dtos.UserDTO;
import br.com.kirgh.app.entities.User;
import br.com.kirgh.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@SuppressWarnings("unused")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(UserDTO userDTO) {
        if (userRepository.existsById(userDTO.cpf())) {
            return null;
        } else {
            return userRepository.save(new User(userDTO));
        }
    }
}
