package ru.sakaev.microServPey.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "balance")
    private double balance;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "wallet_owner_id") // Название колонки для владельца кошелька
    private User walletOwner;

    // Метод для подсчета общей стоимости покупки
    public double totalPrice(double price, int quantity) {
        return price * quantity;
    }

    // Другие геттеры и сеттеры
}
