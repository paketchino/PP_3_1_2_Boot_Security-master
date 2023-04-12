package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception.MyUserNotFoundException;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserEntity;
import ru.kata.spring.boot_security.demo.service.interfaces.RoleService;
import ru.kata.spring.boot_security.demo.service.interfaces.UserService;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public String adminPanel(Model model, Principal principal) {
        if (userService.findUserByUsername(principal.getName()).isEmpty()) {
            throw new MyUserNotFoundException("User not found exception");
        }
        model.addAttribute("edit_user", new UserEntity());
        model.addAttribute("roles", roleService.getAllRole());
        model.addAttribute("new_user", new UserEntity());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("auth", userService.findUserByUsername(principal.getName()).get());
        return "admin";
    }

    @GetMapping("/user")
    public String getUser(Principal principal, Model model) {
        if (userService.findUserByUsername(principal.getName()).isEmpty()) {
            throw new MyUserNotFoundException("User not found exception");
        }
        model.addAttribute("user", userService.findUserByUsername(principal.getName()).get());
        return "user";
    }

    @PostMapping("/admin/addUser")
    public String postAddUser(@ModelAttribute UserEntity new_user,
                              @RequestParam("rol") Set<Role> roles) {
        userService.save(new_user, roles);
        return "redirect:/admin";
    }

    @PostMapping("/admin/deleteUser/{id}")
    public String postDeleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/updateUser")
    public String postUpdateUser(@ModelAttribute @Valid UserEntity user,
                                 @RequestParam("rol") Set<Role> roles) {
        userService.save(user, roles);
        return "redirect:/admin";
    }

}
