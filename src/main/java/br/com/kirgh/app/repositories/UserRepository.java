package br.com.kirgh.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.kirgh.app.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
