package ru.sakaevrs.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ProjectDTO {

    // Геттеры и сеттеры
    private String name;
    private String description;
    private LocalDate createdDate;

}