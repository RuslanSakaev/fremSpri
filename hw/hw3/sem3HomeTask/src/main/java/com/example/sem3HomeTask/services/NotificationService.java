package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void notifyUser(User user, String message) {
        // Вывод пользовательского сообщения вместе с информацией о пользователе
        System.out.println("Уведомление для пользователя: " + user.getName() +
                ". Сообщение: " + message);
    }
}

