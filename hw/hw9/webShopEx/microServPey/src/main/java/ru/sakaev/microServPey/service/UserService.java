package ru.sakaev.microServPey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sakaev.microServPey.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    public UserService(UserRepository userRepository) {
    }

    // Другие методы для работы с пользователями
}
