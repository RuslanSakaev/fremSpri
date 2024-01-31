package ru.sakaev.SimpleWebAppSMVC.repository;

import ru.sakaev.SimpleWebAppSMVC.model.User;

import java.util.Optional;

// Интерфейс для работы с пользователями в базе данных
public interface UserRepository {
    Optional<User> findByUsername(String username);
    void save(User user);
    // TODO реализовать другие методы
}