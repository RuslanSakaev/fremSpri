package ru.sakaev.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor // создает конструктор без параметров, который требуется JPA
@Entity // указывает, что класс Note является сущностью, которая будет сопоставлена с таблицей в базе данных
public class Note {
    // Геттеры и сеттеры
    @Id // указывает, что поле id является первичным ключом для сущности.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // значение для поля id будет генерироваться автоматически.
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createdAt;

    // Конструктор с параметрами
    public Note(String title, String content, LocalDateTime createdAt) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

}
