package ru.sakaev.SimpleWebAppSMVC.service.impl;

import ru.sakaev.SimpleWebAppSMVC.model.User;
import ru.sakaev.SimpleWebAppSMVC.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Override
    public void save(User user) {
        // Здесь вы можете реализовать сохранение в базу данных или другую логику
        System.out.println("Saving user: " + user.getUsername());
    }
}