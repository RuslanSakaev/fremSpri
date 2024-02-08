package ru.sakaev.rickAndMorty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Info {
    // Геттеры и сеттеры
    private int count;
    private int pages;
    private String next;
    private String prev;

    public Info(int size) {
    }

}
