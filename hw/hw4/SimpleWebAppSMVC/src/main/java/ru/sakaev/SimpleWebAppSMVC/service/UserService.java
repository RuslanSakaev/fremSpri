package ru.sakaev.SimpleWebAppSMVC.service;

import ru.sakaev.SimpleWebAppSMVC.model.User;

// Интерфейс для выполнения операций с пользователями
public interface UserService {

    void registerUser(User user); // Регистрация пользователя
}