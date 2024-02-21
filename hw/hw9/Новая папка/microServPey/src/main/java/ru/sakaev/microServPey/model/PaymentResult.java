package ru.sakaev.microServPey.model;

import lombok.Data;

@Data
public class PaymentResult {
    private String transactionId;
    private boolean success;
    private String errorDetails;

    public PaymentResult(boolean b) {
    }

    // Конструкторы, геттеры и сеттеры
}