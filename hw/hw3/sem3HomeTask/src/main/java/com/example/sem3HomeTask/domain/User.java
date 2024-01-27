package com.example.sem3HomeTask.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty

    private String name;
    @JsonProperty
    private int age;
    @JsonProperty
    private String email;

    public User() {
        // Конструктор без аргументов
    }

    // Конструктор с параметрами
    public User(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    // Геттеры и сеттеры для всех полей
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
