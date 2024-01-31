package ru.sakaev.SimpleWebAppSMVC.service.impl;

import ru.sakaev.SimpleWebAppSMVC.model.User;
import ru.sakaev.SimpleWebAppSMVC.repository.UserRepository;
import ru.sakaev.SimpleWebAppSMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        // Поиск пользователя по имени
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        // Проверка, существует ли пользователь с таким именем
        if (existingUser.isPresent()) {
            // Логика обработки, если пользователь уже существует
            System.out.println("User with username " + user.getUsername() + " already exists.");
        } else {
            // Логика регистрации нового пользователя
            userRepository.save(user);
            System.out.println("User registered: " + user.getUsername());
        }
    }
}