package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.exception.MyUserNotFoundException;
import ru.kata.spring.boot_security.demo.model.UserEntity;
import ru.kata.spring.boot_security.demo.service.interfaces.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminPanel(Model model, Principal principal) {
        if (userService.findUserByUsername(principal.getName()).isEmpty()) {
            throw new MyUserNotFoundException("User not found exception");
        }
        model.addAttribute("user", userService.findUserByUsername(principal.getName()).get());
        model.addAttribute("users", userService.getAllUsers());
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

    @GetMapping("/admin/regAdmin")
    public String getRegAdmin() {
        return "regAdmin";
    }

    @PostMapping("/admin/regAdmin")
    public String regAdmin(@Valid @ModelAttribute UserEntity user) {
        userService.saveAdmin(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/addUser")
    public String getAddUser() {
        return "addUser";
    }

    @PostMapping("/admin/addUser")
    public String postAddUser(@Valid @ModelAttribute("user")
                                  UserEntity user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/deleteUser")
    public String getDeleteUser(@RequestParam("id") Long id, Model model) {
        if (userService.findById(id).isEmpty()) {
            throw new MyUserNotFoundException("User not found exception");
        }
        model.addAttribute("user", userService.findById(id).get());
        return "deleteUser";
    }

    @PostMapping("/admin/deleteUser")
    public String postDeleteUser(@ModelAttribute UserEntity user) {
        userService.delete(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/updateUser")
    public String getUpdateUser(@RequestParam("id") Long id, Model model) {
        if (userService.findById(id).isEmpty()) {
            throw new MyUserNotFoundException("User not found exception");
        }
        model.addAttribute("user", userService.findById(id).get());
        return "updateUser";
    }

    @PostMapping("/admin/updateUser")
    public String postUpdateUser(@ModelAttribute @Valid UserEntity user) {
        userService.save(user);
        return "redirect:/admin";
    }

}
