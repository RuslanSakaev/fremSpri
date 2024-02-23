package ru.sakaev.backEndApp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.sakaev.backEndApp.model.Product;
import ru.sakaev.backEndApp.service.ProductService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ProductController productController = new ProductController(productService);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setDescription("Description for product 1");
        product1.setPrice(10.0);
        product1.setQuantity(5);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setDescription("Description for product 2");
        product2.setPrice(20.0);
        product2.setQuantity(10);

        List<Product> productList = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(productList);

        mockMvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].name").value("Product 1"))
                .andExpect(jsonPath("$[1].name").value("Product 2"));
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product product = new Product();
        product.setName("New Product");
        product.setDescription("Description for new product");
        product.setPrice(30.0);
        product.setQuantity(15);

        when(productService.createProduct(any())).thenReturn(product);

        mockMvc.perform(post("/api/products/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Product"))
                .andExpect(jsonPath("$.description").value("Description for new product"))
                .andExpect(jsonPath("$.price").value(30.0))
                .andExpect(jsonPath("$.quantity").value(15));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Long productId = 1L;
        Product existingProduct = new Product();
        existingProduct.setId(productId);
        existingProduct.setName("Existing Product");
        existingProduct.setDescription("Description for existing product");
        existingProduct.setPrice(25.0);
        existingProduct.setQuantity(20);

        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setName("Updated Product");
        updatedProduct.setDescription("Updated description");
        updatedProduct.setPrice(30.0);
        updatedProduct.setQuantity(25);

        when(productService.getProductById(productId)).thenReturn(existingProduct);
        when(productService.updateProduct(productId, updatedProduct)).thenReturn(updatedProduct);

        mockMvc.perform(put("/api/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product"))
                .andExpect(jsonPath("$.description").value("Updated description"))
                .andExpect(jsonPath("$.price").value(30.0))
                .andExpect(jsonPath("$.quantity").value(25));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Long productId = 1L;

        mockMvc.perform(delete("/api/products/{id}", productId))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).deleteProduct(productId);
    }

    // Метод для сериализации объекта в JSON строку
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
