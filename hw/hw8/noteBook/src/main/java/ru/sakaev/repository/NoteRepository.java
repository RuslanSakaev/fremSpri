package ru.sakaev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sakaev.model.Note;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    // Дополнительные методы для работы с заметками

    // Найти все заметки с заданным заголовком
    List<Note> findByTitle(String title);

    // Найти все заметки, содержащие заданное слово в заголовке или содержании
    List<Note> findByTitleContainingOrContentContaining(String title, String content);

    // Найти все заметки, созданные после определенной даты
    List<Note> findByCreatedAtAfter(LocalDateTime date);

    // Найти все заметки, созданные до определенной даты
    List<Note> findByCreatedAtBefore(LocalDateTime date);

    // Найти все заметки, созданные в определенном промежутке времени
    List<Note> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
