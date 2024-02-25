package ru.sakaev.backEndApp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sakaev.backEndApp.model.Product;
import ru.sakaev.backEndApp.repository.ProductRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testGetAllProducts() {
        // Arrange
        List<Product> productList = Arrays.asList(
                new Product(1L, "Product 1", "Description 1", 10.0, 5),
                new Product(2L, "Product 2", "Description 2", 20.0, 3)
        );
        when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<Product> result = productService.getAllProducts();

        // Assert
        assertEquals(2, result.size());
        assertEquals(productList.get(0), result.get(0));
        assertEquals(productList.get(1), result.get(1));
    }

    @Test
    void testGetProductById() {
        // Arrange
        Product product = new Product(1L, "Product 1", "Description 1", 10.0, 5);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        Product result = productService.getProductById(1L);

        // Assert
        assertEquals(product, result);
    }

    @Test
    void testCreateProduct() {
        // Arrange
        Product productToSave = new Product(null, "Product 1", "Description 1", 10.0, 5);
        Product savedProduct = new Product(1L, "Product 1", "Description 1", 10.0, 5);
        when(productRepository.save(productToSave)).thenReturn(savedProduct);

        // Act
        Product result = productService.createProduct(productToSave);

        // Assert
        assertEquals(savedProduct, result);
    }

    @Test
    void testDeleteProduct() {
        // Arrange

        // Act
        productService.deleteProduct(1L);

        // Assert
        verify(productRepository, times(1)).deleteById(1L);
    }
}
