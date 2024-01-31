package ru.sakaev.SimpleWebAppSMVC.model;

// Модель пользователя, используемая для регистрации
public class User {

    private String username; // Имя пользователя
    private String password; // Пароль пользователя

    // Геттер для имени пользователя
    public String getUsername() {
        return username;
    }

    // Сеттер для имени пользователя
    public void setUsername(String username) {
        this.username = username;
    }

    // Геттер для пароля пользователя
    public String getPassword() {
        return password;
    }

    // Сеттер для пароля пользователя
    public void setPassword(String password) {
        this.password = password;
    }
}