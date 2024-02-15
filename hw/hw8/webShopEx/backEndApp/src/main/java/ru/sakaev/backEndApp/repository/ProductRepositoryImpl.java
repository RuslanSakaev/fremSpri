package ru.sakaev.backEndApp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import ru.sakaev.backEndApp.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final List<Product> products = new ArrayList<>();

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @Override
    public Product save(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public void deleteById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
