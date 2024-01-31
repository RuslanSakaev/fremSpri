package ru.sakaev.SimpleWebAppSMVC.controller;

import ru.sakaev.SimpleWebAppSMVC.model.User;
import ru.sakaev.SimpleWebAppSMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController {

    private final UserService userService;

    // Инъекция зависимости UserService через конструктор
    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    // Обработчик GET-запроса для "/registration"
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // Добавление нового пользователя в модель
        return "registration"; // Возвращение имени представления
    }

    // Обработчик POST-запроса для "/register"
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user); // Регистрация пользователя через сервис
        return "registration-success"; // Возвращение имени представления
    }
}