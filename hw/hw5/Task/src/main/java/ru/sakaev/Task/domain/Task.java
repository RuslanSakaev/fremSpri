package ru.sakaev.Task.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

// Класс, представляющий сущность "Задача".
@Entity
@Data
public class Task {
    // Идентификатор задачи.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Описание задачи.
    private String description;

    // Статус задачи (выбирается из перечисления TaskStatus).
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    // Дата и время создания задачи.
    private LocalDateTime creationDate;
}
