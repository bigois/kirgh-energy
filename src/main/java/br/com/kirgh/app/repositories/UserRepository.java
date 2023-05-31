package br.com.kirgh.app.repositories;

import br.com.kirgh.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
