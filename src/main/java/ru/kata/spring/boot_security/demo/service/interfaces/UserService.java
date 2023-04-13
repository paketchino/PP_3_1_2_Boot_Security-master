package ru.kata.spring.boot_security.demo.service.interfaces;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> findById(Long id);

    User save(User user, Set<Role> roles);

    Optional<User> findUserByUsername(String username);

    void delete(Long id);
}
