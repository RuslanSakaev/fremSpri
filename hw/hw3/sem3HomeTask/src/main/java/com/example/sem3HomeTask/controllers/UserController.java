package com.example.sem3HomeTask.controllers;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")//localhost:8080/user
public class UserController {


    @Autowired
    private RegistrationService service;

    /**
     * Добавляет пользователя, используя данные, переданные через параметры HTTP-запроса.
     * @param name Имя пользователя.
     * @param age Возраст пользователя.
     * @param email Электронная почта пользователя.
     * @return Сообщение о добавлении пользователя.
     */
    @PostMapping("/add")
    public String userAddFromParam(
            @RequestParam("name") String name,
            @RequestParam("age") int age,
            @RequestParam("email") String email) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setAge(age);
        newUser.setEmail(email);

        // Добавляем пользователя через сервис
        service.getDataProcessingService().addUserToList(newUser);

        return "User added with name: " + name;
    }

    @GetMapping
    public List<User> userList() { return service.getDataProcessingService().getRepository().getUsers(); }

    @PostMapping("/body")
    public String userAddFromBody(@RequestBody User user)
    {
        service.getDataProcessingService().getRepository().getUsers().add(user);
        return "User added from body!";
    }
}
