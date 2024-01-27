package com.example.AdminPanelV1.controllers.REST;

import com.example.AdminPanelV1.domain.User;
import com.example.AdminPanelV1.services.tasks.TaskServiceImpl;
import com.example.AdminPanelV1.services.users.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/tasks")
public class RestTasksController {
    /**
     * Сервис реализации логики задач
     */
    private TaskServiceImpl taskService;

    /**
     * Сервис реализации логики управления пользователями
     */
    private UserServiceImpl userService;

    /**
     * Get запрос для получения списка запросов
     *
     * @return список задач, которые можно реализовать в рамках приложения
     */
    @GetMapping
    public List<String> getAllTasks() {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return tasks;
    }

    /**
     * Get запрос на сортировку пользователей
     * localhost:8080/tasks/sort
     *
     * @return Отсортированный список пользователей
     */
    @GetMapping("/sort")
    public List<User> sortUsersByAge() {
        return taskService.sortUsersByAge(userService.getAllUsers());
    }

    /**
     * Get запрос на фильтрацию пользователей
     * localhost:8080/filter/{age}
     *
     * @param age - передаваемый параметр (возраст)
     * @return отфильтрованный список пользователей
     */
    @GetMapping("/filter/{age}")
    public List<User> filterUsersByAge(@PathVariable int age) {
        return taskService.filterUsersByAge(userService.getAllUsers(), age);
    }

    /**
     * Get запрос для подсчета среднего возраста пользователей
     * localhost:8080/tasks/calk
     *
     * @return Средний возраст пользователей в строковом формате
     */
    @GetMapping("/calk")
    public String calculateAverageAge() {
        return taskService.calculateAverageAge(userService.getAllUsers());
    }
}
