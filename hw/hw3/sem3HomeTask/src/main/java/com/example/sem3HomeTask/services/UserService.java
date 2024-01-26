package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import com.example.sem3HomeTask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final NotificationService notificationService;
    private final UserRepository userRepository;

    @Autowired
    public UserService(NotificationService notificationService, UserRepository userRepository) {
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }

    public void addUser(User user) {
        // Добавление пользователя в репозиторий
        userRepository.getUsers().add(user);

        // Отправка уведомления о добавлении нового пользователя
        notificationService.notifyUser(user, "Вы успешно зарегистрированы!");
    }

}
