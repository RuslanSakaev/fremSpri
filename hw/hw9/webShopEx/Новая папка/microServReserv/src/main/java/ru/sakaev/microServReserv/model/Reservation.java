package ru.sakaev.microServReserv.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Reservation {
    private String reservationId;
    private String itemId;
    @Setter
    @Getter
    private int quantity;
    @Setter
    @Getter
    private Long productId;

}