package com.example.AdminPanelV1.controllers.WEB;

import com.example.AdminPanelV1.domain.User;
import com.example.AdminPanelV1.services.tasks.TaskServiceImpl;
import com.example.AdminPanelV1.services.users.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@AllArgsConstructor
@Log
@Controller
public class TasksController {
    /**
     * Сервис реализации логики задач
     */
    private TaskServiceImpl taskService;

    /**
     * Сервис реализации логики управления пользователями
     */
    private UserServiceImpl userService;

    /**
     * Функция реализации фильтрации пользователей
     *
     * @param model способ передачи значени шаблонизатору
     * @param age   возраст пользователя
     * @return возвращаем страницу всех пользователей
     */
    @PostMapping("/users-filter")
    public String filterUsersDB(Model model, @RequestParam("age") int age) {
        List<User> users = taskService.filterUsersByAge(userService.getAllUsers(), age);
        model.addAttribute("users", users);
        return "user-list";
    }

    /**
     * Функция реализации сортировки пользователей
     *
     * @param model способ передачи значени шаблонизатору
     * @return возвращаем страницу всех пользователей
     */
    @GetMapping("/users-sort")
    public String sortUsersDB(Model model) {
        List<User> users = taskService.sortUsersByAge(userService.getAllUsers());
        model.addAttribute("users", users);
        return "user-list";
    }
}
