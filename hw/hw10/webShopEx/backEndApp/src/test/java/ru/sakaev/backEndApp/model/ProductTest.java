package ru.sakaev.backEndApp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void testConstructorAndGetters() {
        // Создаем продукт с заданными параметрами
        Long id = 1L;
        String name = "Test Product";
        String description = "Test Description";
        double price = 10.0;
        int quantity = 5;

        Product product = new Product(id, name, description, price, quantity);

        // Проверяем, что конструктор правильно устанавливает значения полей
        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertEquals(quantity, product.getQuantity());
    }

    @Test
    public void testDefaultConstructor() {
        // Создаем продукт с использованием конструктора по умолчанию
        Product product = new Product();

        // Проверяем, что поля имеют значения по умолчанию (null или 0)
        assertNull(product.getId());
        assertNull(product.getName());
        assertNull(product.getDescription());
        assertEquals(0.0, product.getPrice());
        assertEquals(0, product.getQuantity());
    }

    @Test
    public void testSetters() {
        // Создаем продукт
        Product product = new Product();

        // Устанавливаем значения полей
        Long id = 1L;
        String name = "Test Product";
        String description = "Test Description";
        double price = 10.0;
        int quantity = 5;

        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);

        // Проверяем, что значения полей установлены правильно
        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(description, product.getDescription());
        assertEquals(price, product.getPrice());
        assertEquals(quantity, product.getQuantity());
    }

    public void setId(long l) {
    }

    public void setName(String s) {
    }

    public void setDescription(String s) {
    }

    public void setPrice(double v) {
    }

    public void setQuantity(int i) {
    }
}
