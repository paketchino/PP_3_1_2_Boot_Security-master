package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDaoRepository extends CrudRepository<User, Long> {

    @Modifying
    User save(User user);

    @Query("from User")
    List<User> getAllUser();

    @Query("from User as u where u.login =:login")
    Optional<User> findUserByLogin(@Param("login") String login);

    @Modifying
    void delete(User user);

    Optional<User> findUserById(Long id);

}
