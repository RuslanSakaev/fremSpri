package ru.sakaev.backEndApp.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private double price;
    // Другие поля, геттеры и сеттеры
}
