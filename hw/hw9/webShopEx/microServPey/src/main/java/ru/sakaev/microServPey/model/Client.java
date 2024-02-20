package ru.sakaev.microServPey.model;

import jakarta.persistence.*;
import lombok.Setter;

import java.util.Date;

@Entity
public class Client  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String name;
    @Setter
    private Date registrationDate;
    @Setter
    private double walletAmount;
}

