package ru.sakaev.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    // TODO Метод может быть расширен для выполнения дополнительных действий,
    // TODO таких как проверка имени пользователя и пароля в базе данных или другом хранилище данных.
    public boolean authenticate(String username, String password) {
        // В этом примере просто проверяем, что имя пользователя и пароль не пустые
        return username != null && !username.isEmpty() &&
                password != null && !password.isEmpty();
    }
}