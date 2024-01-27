package com.example.NotesRest.controllers;

import com.example.NotesRest.models.Note;
import com.example.NotesRest.services.NoteService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequiredArgsConstructor
@RequestMapping("/api")
public class NoteController {
    /**
     * Сервис управления заметками
     */
    private final NoteService noteService;

    /**
     * Получение всех заметок
     *
     * @return список заметок
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(noteService.getAllNotes(), HttpStatus.OK);
    }

    /**
     * Создане новой заметки
     *
     * @param note новая заметка из body
     * @return созданная заметка
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.create(note), HttpStatus.CREATED);
    }

    /**
     * Обновление заметки
     *
     * @param note заметка из body
     * @return обновленная заметка
     */
    @PutMapping
    public ResponseEntity<Note> updateNote(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.update(note), HttpStatus.OK);
    }

    /**
     * Удаление заметки
     *
     * @param id уникальный идентификатор нужной заметки
     * @return возвращаем статус выполнения
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") long id) {
        noteService.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Получить заметку по идентификатору
     *
     * @param id уникальный идентификатор заметки
     * @return заметка
     */
    @GetMapping("{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") long id) {
        return new ResponseEntity<>(noteService.getById(id), HttpStatus.OK);
    }
}
