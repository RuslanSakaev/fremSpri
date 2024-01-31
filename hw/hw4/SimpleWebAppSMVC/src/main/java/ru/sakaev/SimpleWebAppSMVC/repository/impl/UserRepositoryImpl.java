package ru.sakaev.SimpleWebAppSMVC.repository.impl;

import ru.sakaev.SimpleWebAppSMVC.model.User;
import ru.sakaev.SimpleWebAppSMVC.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    // Реализация метода сохранения пользователя в базе данных
    @Override
    public void save(User user) {
        // TODO: Реализовать сохранение в базу данных или другую логику
        // Пример: userRepository.save(user);
        System.out.println("Saving user: " + user.getUsername());

        // Логирование вместо вывода в консоль (библиотеки логирования: SLF4J и Logback)
        // Пример: log.info("User saved: {}", user.getUsername());
    }

    // Реализация метода поиска пользователя по имени
    @Override
    public Optional<User> findByUsername(String username) {
        // TODO: Реализовать поиск пользователя в базе данных или другую логику
        // Пример: return userRepository.findByUsername(username);
        System.out.println("Searching for user: " + username);

        // Возвращаем Optional.empty(), это пример
        return Optional.empty();
    }
}
