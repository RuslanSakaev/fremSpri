package ru.sakaev.SimpleWebAppSMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloWorldController {

    // Обработчик GET-запроса для "/"
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String helloWorld(Model model) {
        model.addAttribute("message", "Привет, мир!"); // Добавление сообщения в модель
        return "hello"; // Возвращение имени представления
    }
}