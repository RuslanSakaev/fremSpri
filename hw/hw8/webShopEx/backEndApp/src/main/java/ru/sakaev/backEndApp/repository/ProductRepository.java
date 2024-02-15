package ru.sakaev.backEndApp.repository;

import java.util.List;
import java.util.Optional;

import ru.sakaev.backEndApp.model.Product;

public interface ProductRepository {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
}
