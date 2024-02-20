package ru.sakaev.microServPey.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class ReservationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Метод для получения ID товара
    @Getter
    private Long productId;
    private int quantity;

    // Геттеры и сеттеры

    public ReservationRequest() {
    }

    public ReservationRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public void setClientId(Long clientId) {
    }
}
