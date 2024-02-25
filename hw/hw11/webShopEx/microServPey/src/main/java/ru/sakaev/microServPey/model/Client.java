package ru.sakaev.microServPey.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date registrationDate;

    private double walletAmount;

    public Client(String name, double walletAmount) {
        this.name = name;
        this.walletAmount = walletAmount;
    }
}
