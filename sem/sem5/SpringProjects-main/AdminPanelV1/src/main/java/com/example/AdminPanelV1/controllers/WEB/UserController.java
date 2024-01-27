package com.example.AdminPanelV1.controllers.WEB;

import com.example.AdminPanelV1.domain.User;
import com.example.AdminPanelV1.services.tasks.TaskServiceImpl;
import com.example.AdminPanelV1.services.users.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Log
@Controller
public class UserController {
    /**
     * Сервис реализации логики задач
     */
    private TaskServiceImpl taskService;

    /**
     * Сервис реализации логики управления пользователями
     */
    private UserServiceImpl userService;

    /**
     * Нахождение всех пользователей
     *
     * @param model управление аргументами шаблонизатора
     * @return возвращаем страницу всех пользователей
     */
    @GetMapping("/users-db/{id}")
    public String findAllDB(Model model, @PathVariable("id") int id) {
        List<User> users = userService.getAllUsers();

        try {
            User user = users.stream().filter(item -> item.getId() == id).findFirst().get();
            if (user.isLogin()){
                String avg = taskService.calculateAverageAge(userService.getAllUsers());
                model.addAttribute("users", users);
                model.addAttribute("average", avg);
                return "user-list";
            } else return "404";
        } catch (Exception e){
            return "404";
        }
    }

    /**
     * Создание пользователей. Страница создания (форма)
     *
     * @param user пользователей
     * @return страница создания пользователей
     */
    @GetMapping("/user-create")
    public String getCreateFormDB(User user, Model model) {
        model.addAttribute("title", "Create user:");
        return "user-create.html";
    }

    /**
     * Реализация создания пользователя
     *
     * @param user пользователь
     * @return список всех юзеров
     */
    @PostMapping("/user-create")
    public String createUserDB(User user) {
        userService.createUser(user);
        return "redirect:/users-db";
    }

    /**
     * Обновление всех пользователей. Страница - форма
     *
     * @param user пользователь
     * @return страница обновления пользователя
     */
    @GetMapping("/user-update/{id}")
    public String updateForm(User user) {
        return "user-update";
    }

    /**
     * Функция реализации обновления пользователя
     *
     * @param user пользователь
     * @return страница всех пользователей
     */
    @PostMapping("/user-update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/users-db";
    }

    /**
     * Удаление пользователя по ID
     *
     * @param id уникальный идентификатор пользователя
     * @return страница всех пользователей
     */
    @GetMapping("/user-delete/{id}")
    public String delUser(@PathVariable("id") int id) {
        userService.delUser(id);
        return "redirect:/users-db";
    }

}
