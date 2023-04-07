package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);

    List<User> getAllUser();

    void update(User user);

    void delete(User user);

    Optional<User> findById(Long id);
}
