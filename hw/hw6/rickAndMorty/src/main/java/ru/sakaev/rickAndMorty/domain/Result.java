package ru.sakaev.rickAndMorty.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Result<T> {
    // Геттеры и сеттеры
    private List<T> results;
    private Info info;

}
