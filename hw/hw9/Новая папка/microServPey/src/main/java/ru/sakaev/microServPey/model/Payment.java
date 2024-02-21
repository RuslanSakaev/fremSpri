package ru.sakaev.microServPey.model;

import lombok.Data;

@Data
public class Payment {
    private String id;
    private double amount;
    private String currency;
    private String status;
    private String errorDetails;

    // Конструкторы, геттеры и сеттеры
}
