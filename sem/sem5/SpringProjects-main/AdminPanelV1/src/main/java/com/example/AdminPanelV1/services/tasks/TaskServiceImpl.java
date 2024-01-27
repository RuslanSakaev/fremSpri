package com.example.AdminPanelV1.services.tasks;

import com.example.AdminPanelV1.domain.User;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис управления задачами.
 * Имплементируем интерфейс
 */
@Service
public class TaskServiceImpl implements TaskService {

    /**
     * Сортировка пользователей
     *
     * @param users пользователи
     * @return список отсортированных пользователей
     */
    @Override
    public List<User> sortUsersByAge(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    /**
     * Фильтрация пользователей
     *
     * @param users пользователи
     * @param age   возраст пользователя
     * @return список отфильтрованных пользователей
     */
    @Override
    public List<User> filterUsersByAge(List<User> users, int age) {
        return users.stream()
                .filter(user -> user.getAge() >= age)
                .collect(Collectors.toList());
    }

    /**
     * Среднее значение возраста всех пользователей
     *
     * @param users пользователи
     * @return Строка со средним значением
     */
    @Override
    public String calculateAverageAge(List<User> users) {
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0) + " = average age";
    }
}
