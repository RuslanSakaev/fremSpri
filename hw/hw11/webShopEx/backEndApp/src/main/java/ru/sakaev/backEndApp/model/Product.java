package ru.sakaev.backEndApp.model; // Объявление пакета для класса Product

import jakarta.persistence.*; // Импорт аннотаций JPA
import lombok.Data; // Импорт аннотации Lombok для создания геттеров, сеттеров и других методов

@Entity // Аннотация, указывающая, что класс является сущностью JPA
@Data // Аннотация Lombok, генерирующая геттеры, сеттеры, методы toString и equals/hashCode
@Table(name = "product") // Аннотация для указания имени таблицы в базе данных
public class Product { // Объявление класса Product

    @Id // Аннотация, указывающая на первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Стратегия генерации значений для первичного ключа
    private Long id; // Поле для хранения идентификатора продукта

    @Column(name = "name") // Аннотация для указания имени столбца в базе данных
    private String name; // Поле для хранения названия продукта

    @Column(name = "description") // Аннотация для указания имени столбца в базе данных
    private String description; // Поле для хранения описания продукта

    @Column(name = "price") // Аннотация для указания имени столбца в базе данных
    private double price; // Поле для хранения цены продукта

    @Column(name = "quantity") // Аннотация для указания имени столбца в базе данных
    private int quantity; // Поле для хранения количества продукта на складе

    // Конструктор с параметрами для создания объекта Product
    public Product(Long id, String name, String description, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // Конструктор без параметров
    public Product() {

    }

}
