package ru.sakaev.backEndApp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.sakaev.backEndApp.model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetAllProducts() {
        ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity("/api/products", Product[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Product[] products = responseEntity.getBody();
        assertNotNull(products);
        assertEquals(2, products.length); // Предполагается, что возвращаются два продукта
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setName("New Product");
        product.setDescription("Description for new product");
        product.setPrice(30.0);
        product.setQuantity(15);

        ResponseEntity<Product> responseEntity = restTemplate.postForEntity("/api/products/create", product, Product.class);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        Product createdProduct = responseEntity.getBody();
        assertNotNull(createdProduct);
        assertEquals("New Product", createdProduct.getName());
        assertEquals("Description for new product", createdProduct.getDescription());
        assertEquals(30.0, createdProduct.getPrice());
        assertEquals(15, createdProduct.getQuantity());
    }

    // Другие тесты (например, для обновления и удаления продукта) могут быть написаны аналогичным образом.
}

