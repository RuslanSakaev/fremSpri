package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserService userService;
    private final NotificationService notificationService;

    @Autowired
    public RegistrationService(UserService userService, NotificationService notificationService) {
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public void processRegistration(String name, int age, String email) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setAge(age);
        newUser.setEmail(email);

        userService.addUser(newUser); // Предполагаем, что такой метод существует в UserService

        notificationService.notifyUser(newUser, "Welcome to the system, " + name + "!");
    }
}

