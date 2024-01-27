package com.example.sem3HomeTask.controllers;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")//localhost:8080/user
public class UserController {

    @Autowired
    private RegistrationService service;

    /**
     * Получает список пользователей.
     * @return Список пользователей
     */
    @GetMapping
    public List<User> userList() {
        return service.getDataProcessingService().getAllUsers();
    }

    /**
     * Добавляет пользователя, используя данные, переданные через параметры HTTP-запроса.
     * @param name Имя пользователя.
     * @param age Возраст пользователя.
     * @param email Электронная почта пользователя.
     * @return Сообщение о добавлении пользователя.
     */
    @PostMapping("/param")
    public String userAddFromParam(@RequestParam String name,
                                   @RequestParam int age,
                                   @RequestParam String email) {
        service.processRegistration(name, age, email);
        return "User added from parameters!";
    }

    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user) {
        System.out.println("Received user: " + user); // логирование
        service.registerUser(user);
        return "User added from body!";
    }

    @PostMapping("/user/body")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        System.out.println("Received user: " + user.getName() + ", " + user.getAge() + ", " + user.getEmail()); // логирование
        service.processRegistration(user.getName(), user.getAge(), user.getEmail());
        return ResponseEntity.ok("User added");
    }
}
