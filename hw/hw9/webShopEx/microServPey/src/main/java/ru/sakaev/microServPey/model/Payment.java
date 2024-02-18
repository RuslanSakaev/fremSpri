package ru.sakaev.microServPey.model;

import jakarta.persistence.*;
import lombok.Getter;
import ru.sakaev.microServReserv.model.Product;


import java.util.List;

@Entity
@Table(name = "payments")
public class Payment {
    @Getter
    private List<Product> products;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_amount")
    private double totalAmount;

    // Другие поля, если необходимо

    // Геттеры и сеттеры
}
