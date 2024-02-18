package ru.sakaev.microServPey.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @OneToOne(mappedBy = "walletOwner")
    private Wallet wallet;

    // Геттеры и сеттеры
}
