package ru.sakaev.SimpleWebAppSMVC.service.impl;


import ru.sakaev.SimpleWebAppSMVC.model.User;
import ru.sakaev.SimpleWebAppSMVC.repository.UserRepository;
import ru.sakaev.SimpleWebAppSMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        // Ваша логика регистрации пользователя
        userRepository.save(user);
    }
}