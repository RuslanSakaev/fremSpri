package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// Контроллер для управления взаимодействием с пользователями
@Controller
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users") // Обработка GET-запроса на путь "/users"
    public String findAll(Model model){
        List<User> users = userService.findAll();

        model.addAttribute("users", users);
        log.info("Retrieved all users");
        return "user-list";
    }

    // Обработка GET-запроса на путь "/users/new"
    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    // Обработка POST-запроса на путь "/users/new"
    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    // Обработка GET-запроса на путь "/users/delete/{id}"
    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    // Обработка GET-запроса на путь "/users/edit/{id}"
    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        User user = userService.getOne(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    // Обработка POST-запроса на путь "/users/edit"
    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.update(user);
        return "redirect:/users";
    }
}
