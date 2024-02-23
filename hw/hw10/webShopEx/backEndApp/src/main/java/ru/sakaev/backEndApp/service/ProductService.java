package ru.sakaev.backEndApp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sakaev.backEndApp.model.Product;
import ru.sakaev.backEndApp.repository.ProductRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Transactional
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);
        if (existingProduct != null) {
            BeanUtils.copyProperties(product, existingProduct, "id"); // Исключаем обновление id
            return productRepository.save(existingProduct);
        } else {
            throw new RuntimeException("Продукт не найден с id: " + id);
        }
    }
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
