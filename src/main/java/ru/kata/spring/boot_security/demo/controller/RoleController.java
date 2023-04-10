package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.interfaces.RoleService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/addRole")
    public String getAddRole() {
        return "addRole";
    }

    @PostMapping("/addRole")
    public String addRule(@ModelAttribute @Valid Role role) {
        roleService.save(role);
        return "redirect:/admin";
    }
}
