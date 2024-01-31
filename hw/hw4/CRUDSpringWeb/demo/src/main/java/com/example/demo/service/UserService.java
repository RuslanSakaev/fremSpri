package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Сервис для управления операциями с пользователями
@Service
public class UserService {
    private final UserRepository userRepository;

    // Конструктор с внедрением зависимости UserRepository
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Получение списка всех пользователей
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Сохранение пользователя
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Удаление пользователя по идентификатору
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    // Обновление данных пользователя
    public User update(User user) {
        return userRepository.update(user);
    }

    // Получение пользователя по идентификатору
    public User getOne(int id) {
        return userRepository.getOne(id);
    }
}
