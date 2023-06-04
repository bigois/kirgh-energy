package br.com.kirgh.app.entities;

import br.com.kirgh.app.enums.UserGender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(length = 11, unique = true)
    private String cpf;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 80, unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "date")
    private Date birthDate;

    @Column(nullable = false, length = 150, columnDefinition = "varchar(1)")
    @Enumerated(EnumType.STRING)
    private UserGender gender;

    // private UserRelation relation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return cpf != null && Objects.equals(cpf, user.cpf);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
