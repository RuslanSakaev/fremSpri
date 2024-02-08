package ru.sakaev.rickAndMorty.domain;

import lombok.Data;

import java.util.List;

@Data
public class Character {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image; // изображение
    private List<String> episodes; // список эпизодов
}
