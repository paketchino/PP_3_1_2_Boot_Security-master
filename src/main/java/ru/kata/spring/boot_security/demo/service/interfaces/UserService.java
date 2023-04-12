package ru.kata.spring.boot_security.demo.service.interfaces;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    List<UserEntity> getAllUsers();

    Optional<UserEntity> findById(Long id);

    UserEntity save(UserEntity user, Set<Role> roles);

    Optional<UserEntity> findUserByUsername(String username);

    void delete(Long id);
}
