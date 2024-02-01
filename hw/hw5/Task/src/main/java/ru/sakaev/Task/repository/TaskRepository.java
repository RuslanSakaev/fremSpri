package ru.sakaev.Task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sakaev.Task.domain.Task;
import ru.sakaev.Task.domain.TaskStatus;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // Интерфейс-репозиторий для работы с сущностью Task в базе данных.

    // Метод для поиска задач по их статусу.
    List<Task> findByStatus(TaskStatus status);
}
