package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query("from UserEntity as u join fetch u.roles")
    List<UserEntity> getAllUsers();

    @Query("from UserEntity as u join fetch u.roles where u.id = :id")
    Optional<UserEntity> findById(@Param("id") Long id);

    @Modifying
    UserEntity save(UserEntity user);

    @Modifying
    void delete(UserEntity user);

    @Query("from UserEntity as u join fetch u.roles where u.username = :username")
    Optional<UserEntity> findUserByUsername(@Param("username") String username);

}
