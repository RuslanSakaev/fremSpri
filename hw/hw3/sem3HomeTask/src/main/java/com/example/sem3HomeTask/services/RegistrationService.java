package com.example.sem3HomeTask.services;

import com.example.sem3HomeTask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final DataProcessingService dataProcessingService;
    private final UserService userService;
    private final NotificationService notificationService;

    /**
     * Конструктор для внедрения зависимостей.
     * @param dataProcessingService служба для обработки данных пользователей
     * @param userService служба для создания новых пользователей
     * @param notificationService служба для отправки уведомлений
     */
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
        User newUser = new User(name, age, email); // Создание нового пользователя
        registerUser(newUser); // Добавление пользователя в репозиторий
    }
    // Отправка уведомления
    public void registerUser(User user) {
        userService.saveUser(user);
        String notificationMessage = "New user registered: " + user.getName();
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
