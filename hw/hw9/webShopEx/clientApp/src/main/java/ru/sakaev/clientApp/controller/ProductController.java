package com.example.clientapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import ru.sakaev.backEndApp.model.Product;

@Controller
public class ProductController {

    private static final String BACKEND_APP_URL = "http://localhost:8080/api/products";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/products")
    public String getProducts(Model model) {
        // Вызываем эндпоинт backEndApp для получения списка товаров
        Product[] products = restTemplate.getForObject(BACKEND_APP_URL, Product[].class);
        // Передаем список товаров в модель для отображения на странице
        model.addAttribute("products", products);
        // Возвращаем имя шаблона представления (HTML страницы)
        return "products";
    }
}
