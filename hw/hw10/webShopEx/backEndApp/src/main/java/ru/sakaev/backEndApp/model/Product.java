package ru.sakaev.backEndApp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private double price;
    @Column(name = "quantity")
    private int quantity;

    public Product(Long l, String s, String s1, double v, int i) {
    }

    public Product() {

    }

    // Геттеры и сеттеры
}


