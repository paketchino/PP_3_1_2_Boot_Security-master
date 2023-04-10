package ru.kata.spring.boot_security.demo.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_role", unique = true, nullable = false)
    @NonNull
    private String nameRole;

    public Role() {
    }

    public Role(String nameRole) {
        this.nameRole = nameRole;
    }

    public Role(Long id, String nameRole) {
        this.id = id;
        this.nameRole = nameRole;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", nameRole='" + nameRole + '\'' +
                '}';
    }
}
