package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserEntity;
import ru.kata.spring.boot_security.demo.service.interfaces.UserService;

import java.util.List;

@Component
public class PreStart implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;

    private final UserService userService;


    public PreStart(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");
        if (!roleRepository.findByNameRole(admin.getNameRole()).isPresent()) {
            roleRepository.save(admin);
        }
        if (!roleRepository.findByNameRole(user.getNameRole()).isPresent()) {
            roleRepository.save(user);
        }
        List<Role> role1 = List.of(admin);
        List<Role> role2 = List.of(user);
        UserEntity adminUser =
                new UserEntity("admin", "password", "Vadim" ,"Vadimov", role1);
        if (!userService.findUserByUsername(adminUser.getUsername()).isPresent()) {
            userService.saveAdmin(adminUser);
        }
        UserEntity userEntity =
                new UserEntity("user", "12345", "Stas", "Stasov", role2);
        if (!userService.findUserByUsername(adminUser.getUsername()).isPresent()) {
            userService.save(userEntity);
        }

    }
}