package ru.sakaev.backEndApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; // Импорт аннотации для внедрения зависимостей
import org.springframework.http.HttpStatus; // Импорт HTTP-статусов
import org.springframework.http.ResponseEntity; // Импорт ResponseEntity для HTTP-ответов
import org.springframework.transaction.annotation.Transactional; // Импорт аннотации Transactional для определения транзакционного поведения
import org.springframework.ui.Model; // Импорт Model для взаимодействия с интерфейсом пользователя
import org.springframework.web.bind.annotation.*; // Импорт аннотаций для определения RESTful-конечных точек

import ru.sakaev.backEndApp.configuration.FileWritingIntegration;
import ru.sakaev.backEndApp.model.Product; // Импорт класса модели Product
import ru.sakaev.backEndApp.service.ProductService; // Импорт класса ProductService

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;


@RestController // Аннотация, указывающая, что этот класс будет обрабатывать RESTful-запросы и ответы
@RequestMapping("/api/products") // Базовое URL-сопоставление для всех конечных точек в этом контроллере
public class ProductController { // Объявление класса ProductController

    private final ProductService productService; // Объявление экземпляра ProductService
    private final FileWritingIntegration.ProductGateway productGateway; // Интерфейс для отправки данных в файл

    private final MeterRegistry meterRegistry;

    @Autowired
    public ProductController(ProductService productService, MeterRegistry meterRegistry, FileWritingIntegration.ProductGateway productGateway) {
        this.productService = productService;
        this.meterRegistry = meterRegistry;
        this.productGateway = productGateway;
    }

    @GetMapping
    @Transactional(readOnly = true)
    public ResponseEntity<List<Product>> getAllProducts() {
        Timer.Sample sample = Timer.start(meterRegistry); // Начало измерения времени выполнения
        List<Product> products = productService.getAllProducts();
        sample.stop(meterRegistry.timer("product.controller.getAllProducts")); // Остановка измерения и регистрация метрики
        meterRegistry.counter("product.controller.getAllProducts.count").increment(); // Увеличение счетчика вызовов метода
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}") // Обработчик GET запроса для получения продукта по ID
    @Transactional(readOnly = true) // Транзакционное поведение метода только для чтения
    public ResponseEntity<Product> getProductById(@PathVariable Long id) { // Метод получения продукта по ID
        Product product = productService.getProductById(id); // Получение продукта по ID через сервис
        if (product != null) { // Если продукт найден
            productGateway.writeToFile("GET", "/api/products/" + id, product.toString()); // Запись данных о запросе в файл
            return new ResponseEntity<>(product, HttpStatus.OK); // Возвращаем продукт с HTTP-статусом ОК
        } else { // Если продукт не найден
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Возвращаем HTTP-статус NOT FOUND
        }
    }

    @PostMapping("/create") // Аннотация для обработки HTTP-запросов типа POST для создания нового ресурса
    @Transactional // Аннотация, определяющая транзакционное поведение метода
    @ResponseStatus(HttpStatus.CREATED) // Аннотация, указывающая код состояния HTTP для успешного создания
    public Product createProduct(@RequestBody Product product) { // Метод для создания нового продукта
        // Отправка данных о запросе в файл
        productGateway.writeToFile("POST", "/api/products/create", product.toString());
        return productService.createProduct(product); // Вызов сервиса для создания нового продукта
    }

    @PutMapping("/{id}") // Обработчик PUT запроса для обновления существующего продукта
    @Transactional // Транзакционное поведение метода
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) { // Метод обновления продукта
        productGateway.writeToFile("PUT", "/api/products/" + id, product.toString()); // Запись данных о запросе в файл
        Product updatedProduct = productService.updateProduct(id, product); // Обновление продукта через сервис
        if (updatedProduct != null) { // Если продукт успешно обновлен
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK); // Возвращаем обновленный продукт с HTTP-статусом ОК
        } else { // Если продукт не найден
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Возвращаем HTTP-статус NOT FOUND
        }
    }

    @DeleteMapping("/{id}") // Обработчик DELETE запроса для удаления продукта по ID
    @Transactional // Транзакционное поведение метода
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) { // Метод удаления продукта
        productGateway.writeToFile("DELETE", "/api/products/" + id, ""); // Запись данных о запросе в файл
        productService.deleteProduct(id); // Удаление продукта через сервис
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Возвращаем HTTP-статус NO CONTENT
    }
}
