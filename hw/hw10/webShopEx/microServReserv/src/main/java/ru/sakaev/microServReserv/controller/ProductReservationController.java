package ru.sakaev.microServReserv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.sakaev.microServReserv.model.Product;
import ru.sakaev.microServReserv.repository.ProductRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/products")
public class ProductReservationController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        // Получаем список товаров из бэкенд-приложения
        ResponseEntity<Product[]> response = restTemplate.getForEntity("http://localhost:8081/api/products", Product[].class);
        List<Product> productList = Arrays.asList(Objects.requireNonNull(response.getBody()));

        // Сохраняем список товаров в базу данных H2 вашего микросервиса
        productRepository.saveAll(productList);

        return ResponseEntity.ok(productList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        // Обновляем товар по ID в бэкенд-приложении
        restTemplate.put("http://localhost:8081/api/products/" + id, product);
        return ResponseEntity.ok().build();
    }
}
