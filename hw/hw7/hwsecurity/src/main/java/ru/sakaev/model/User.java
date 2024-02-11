package ru.sakaev.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    // Геттеры и сеттеры для полей
    private String username;
    private String password;
    private String role;

    // Конструкторы, геттеры и сеттеры

    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

}
