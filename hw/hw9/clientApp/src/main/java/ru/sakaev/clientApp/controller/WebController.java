package com.example.clientapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        return "index"; // Возвращает имя HTML шаблона для домашней страницы
    }

    @GetMapping("/products")
    public String products(Model model) {
        return "products"; // Возвращает имя HTML шаблона для страницы с товарами
    }
}
