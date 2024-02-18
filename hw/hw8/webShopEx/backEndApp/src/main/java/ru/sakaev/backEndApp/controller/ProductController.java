package ru.sakaev.backEndApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.sakaev.backEndApp.model.Product;
import ru.sakaev.backEndApp.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping("/show-products")
    public String showProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("product-list", products);
        return "product-list"; // Название HTML шаблона
    }

    @GetMapping
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @PostMapping("/addToCart/{productId}")
    public ResponseEntity<String> addToCart(@PathVariable Long productId) {
        // Логика добавления товара в корзину
        // productService.addToCart(productId);
        return ResponseEntity.ok("Product added to cart");
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {

        return productService.getProductById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {

        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);
    }
}