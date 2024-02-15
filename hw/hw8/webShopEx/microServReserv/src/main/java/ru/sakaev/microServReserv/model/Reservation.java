package ru.sakaev.microServReserv.model;

import lombok.Data;

@Data
public class Reservation {
    private String reservationId;
    private String itemId;
    private int quantity;
    // Другие поля и методы...
}