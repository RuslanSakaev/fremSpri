package com.example.sem3HomeTask.controllers;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final RegistrationService registrationService;

    @Autowired
    public UserController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/body")
    public ResponseEntity<String> userAddFromParam(@RequestBody User user) {
        registrationService.processRegistration(user.getName(), user.getAge(), user.getEmail());
        return ResponseEntity.ok("User added successfully");
    }

}

