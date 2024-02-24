package ru.sakaev.backEndApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; // Импорт аннотации для внедрения зависимостей
import org.springframework.http.HttpStatus; // Импорт HTTP-статусов
import org.springframework.http.ResponseEntity; // Импорт ResponseEntity для HTTP-ответов
import org.springframework.transaction.annotation.Transactional; // Импорт аннотации Transactional для определения транзакционного поведения
import org.springframework.ui.Model; // Импорт Model для взаимодействия с интерфейсом пользователя
import org.springframework.web.bind.annotation.*; // Импорт аннотаций для определения RESTful-конечных точек

import ru.sakaev.backEndApp.model.Product; // Импорт класса модели Product
import ru.sakaev.backEndApp.service.ProductService; // Импорт класса ProductService

@RestController // Аннотация, указывающая, что этот класс будет обрабатывать RESTful-запросы и ответы
@RequestMapping("/api/products") // Базовое URL-сопоставление для всех конечных точек в этом контроллере
public class ProductController { // Объявление класса ProductController

    private final ProductService productService; // Объявление экземпляра ProductService

    @Autowired // Аннотация для автоматического внедрения зависимостей
    public ProductController(ProductService productService) { // Инъекция зависимости для ProductService через конструктор
        this.productService = productService; // Инициализация экземпляра ProductService
    }

    @GetMapping // Аннотация для обработки HTTP-запросов типа GET
    @Transactional(readOnly = true) // Аннотация, определяющая транзакционное поведение метода
    public ResponseEntity<List<Product>> getAllProducts() { // Метод для получения всех продуктов
        List<Product> products = productService.getAllProducts(); // Вызов сервиса для получения всех продуктов
        return new ResponseEntity<>(products, HttpStatus.OK); // Возвращение списка продуктов с HTTP-статусом OK
    }

    @GetMapping("/{id}") // Аннотация для обработки HTTP-запросов типа GET с динамическим путевым переменным
    @Transactional(readOnly = true) // Аннотация, определяющая транзакционное поведение метода
    public ResponseEntity<Product> getProductById(@PathVariable Long id) { // Метод для получения продукта по ID
        Product product = productService.getProductById(id); // Вызов сервиса для получения продукта по ID
        if (product != null) { // Если продукт существует
            return new ResponseEntity<>(product, HttpStatus.OK); // Возврат продукта с HTTP-статусом OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Возврат HTTP-статуса NOT_FOUND, если продукт не существует
        }
    }

    @PostMapping("/create") // Аннотация для обработки HTTP-запросов типа POST для создания нового ресурса
    @Transactional // Аннотация, определяющая транзакционное поведение метода
    @ResponseStatus(HttpStatus.CREATED) // Аннотация, указывающая код состояния HTTP для успешного создания
    public Product createProduct(@RequestBody Product product) { // Метод для создания нового продукта
        return productService.createProduct(product); // Вызов сервиса для создания нового продукта
    }

    @PutMapping("/{id}") // Аннотация для обработки HTTP-запросов типа PUT для обновления существующего ресурса
    @Transactional // Аннотация, определяющая транзакционное поведение метода
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) { // Метод для обновления существующего продукта
        Product updatedProduct = productService.updateProduct(id, product); // Вызов сервиса для обновления продукта
        if (updatedProduct != null) { // Если продукт успешно обновлен
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK); // Возврат обновленного продукта с HTTP-статусом OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Возврат HTTP-статуса NOT_FOUND, если продукт не существует
        }
    }

    @DeleteMapping("/{id}") // Аннотация для обработки HTTP-запросов типа DELETE для удаления существующего ресурса
    @Transactional // Аннотация, определяющая транзакционное поведение метода
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) { // Метод для удаления продукта
        productService.deleteProduct(id); // Вызов сервиса для удаления продукта
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Возврат HTTP-статуса NO_CONTENT, указывающего на успешное удаление
    }
}
