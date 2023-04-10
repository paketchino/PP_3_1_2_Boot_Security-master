package ru.kata.spring.boot_security.demo.service.interfaces;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    Optional<Role> findById(Long id);

    Optional<Role> findByRole(String role);

    Role save(Role role);

    List<Role> getAllRole();

    Optional<Role> findByNameRole(String name);
}
