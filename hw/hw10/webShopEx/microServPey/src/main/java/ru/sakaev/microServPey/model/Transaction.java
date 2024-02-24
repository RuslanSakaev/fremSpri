package ru.sakaev.microServPey.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private double amount;

    public Transaction(Long clientId, Long productId, int quantity, double price) {
    }

    // Конструкторы, геттеры и сеттеры Lombok сгенерированы автоматически
}
