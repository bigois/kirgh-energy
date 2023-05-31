package br.com.kirgh.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kirgh.app.dtos.UserDTO;
import br.com.kirgh.app.entities.User;
import br.com.kirgh.app.repositories.UserRepository;

@Service
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
