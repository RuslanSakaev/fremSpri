package ru.sakaev.microServReserv.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReservationRequest {
    private Long productId;
    private int quantity;

    // Конструктор, геттеры и сеттеры
    public ReservationRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

}
