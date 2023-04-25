package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("from User as u join fetch u.roles")
    List<User> getAllUsers();

    @Query("from User as u join fetch u.roles where u.id = :id")
    Optional<User> findById(@Param("id") Long id);

    @Query("from User as u join fetch u.roles where u.username = :username")
    Optional<User> findUserByUsername(@Param("username") String username);

}
