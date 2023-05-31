package br.com.kirgh.app.entities;

import java.util.Date;

import br.com.kirgh.app.dtos.UserDTO;
import br.com.kirgh.app.enums.UserGender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "cpf")
@Entity(name = "users")
public class User {
    @Id
    @Column(length = 11, unique = true)
    private String cpf;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 80)
    private String email;

    @Column(nullable = false, columnDefinition = "date")
    private Date birthDate;

    @Column(nullable = false, length = 150, columnDefinition = "varchar(1)")
    @Enumerated(EnumType.STRING)
    private UserGender gender;

    // private UserRelation relation;

    public User(UserDTO userDTO) {
        cpf = userDTO.cpf();
        name = userDTO.name();
        email = userDTO.email();
        birthDate = userDTO.birthDate();
        gender = userDTO.userGender();
        // relation = userDTO.relation();
    }
}
