package ru.sakaev.SimpleWebAppSMVC.repository;

import ru.sakaev.SimpleWebAppSMVC.model.User;

public interface UserRepository {
    void save(User user);
}