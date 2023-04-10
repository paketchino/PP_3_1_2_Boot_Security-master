package ru.kata.spring.boot_security.demo.service.interfaces;

import ru.kata.spring.boot_security.demo.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserEntity> getAllUsers();

    Optional<UserEntity> findById(Long id);

    UserEntity save(UserEntity user);

    void delete(UserEntity user);

    Optional<UserEntity> findUserByUsername(String username);

    UserEntity saveAdmin(UserEntity user);

}
