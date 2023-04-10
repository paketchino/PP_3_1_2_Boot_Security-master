package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    @Query("from Role as r where r.id = :id")
    Optional<Role> findById(@Param("id") Long id);

    @Query("from Role as r where r.nameRole = :role")
    Optional<Role> findByRole(@Param("role") String role);

    @Modifying
    Role save(Role role);

    @Query("from Role")
    List<Role> getAllRole();

    @Query("from Role as r where r.nameRole = :name")
    Optional<Role> findByNameRole(@Param("name") String name);
}
