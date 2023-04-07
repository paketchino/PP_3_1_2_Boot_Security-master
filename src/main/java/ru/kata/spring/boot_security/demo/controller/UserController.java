package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    private final UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/getAllUsers")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "getAllUsers";
    }

    @GetMapping("/getAddUser")
    public String getAddUser() {
        return "getAddUser";
    }

    @PostMapping("/addUser")
    public String postAddUser(@Valid @ModelAttribute User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "redirect:/getAddUser";
        }
        userService.save(user);
        return "redirect:/getAllUsers";
    }

    @GetMapping("/getDeleteUser")
    public String getDeleteUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "getDeleteUser";
    }

    @PostMapping("/postDeleteUser")
    public String postDeleteUser(@ModelAttribute User user) {
        userService.delete(user);
        return "redirect:/getAllUsers";
    }

    @GetMapping("/getUpdateUser")
    public String getUpdateUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id).get());
        return "getUpdateUser";
    }

    @PostMapping("/updateUser")
    public String postUpdateUser(@ModelAttribute @Valid User user) {
        userService.update(user);
        return "redirect:/getAllUsers";
    }

}
