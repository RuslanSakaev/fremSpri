package ru.sakaev.microServReserv.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reservationId;
    private String itemId;

    @Setter
    @Getter
    private int quantity;

    @Setter
    @Getter
    private Long productId;
    @Setter
    @Getter
    private LocalDateTime createdAt;

}
