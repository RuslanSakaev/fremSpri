package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final DataProcessingService dataProcessingService;
    private final UserService userService;
    private final NotificationService notificationService;

    @Autowired
    public RegistrationService(DataProcessingService dataProcessingService,
                               UserService userService,
                               NotificationService notificationService) {
        this.dataProcessingService = dataProcessingService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    /**
     * Обрабатывает регистрацию нового пользователя.
     * @param name Имя пользователя
     * @param age Возраст пользователя
     * @param email Электронная почта пользователя
     */
    public void processRegistration(String name, int age, String email) {
        // Создание нового пользователя
        User newUser = userService.createUser(name, age, email);

        // Добавление пользователя в репозиторий
        dataProcessingService.addUserToList(newUser);

        // Отправка уведомления
        String notificationMessage = "New user registered: " + newUser.getName() + ", Age: " + newUser.getAge() + ", Email: " + newUser.getEmail();
        notificationService.sendNotification(notificationMessage);
    }

    // Геттеры для внедрённых сервисов
    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }

    public UserService getUserService() {
        return userService;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }
}