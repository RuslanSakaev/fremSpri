package com.example.NotesRest.services;

import com.example.NotesRest.models.Note;
import com.example.NotesRest.repository.NoteRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class NoteService {
    /**
     * Интерфейс взаимодействия с Базой Данных
     */
    private final NoteRepository noteRepository;

    /**
     * Получение всех заметок через БД
     *
     * @return список заметок
     */
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Получение заметки по идентификатору в БД
     *
     * @param id уникальный идентификатор
     * @return заметка
     */
    public Note getById(long id) {
        Note note;
        try {
            note = noteRepository.findById(id).orElseThrow(null);
            return note;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Создание заметки в БД
     *
     * @param note заметка из запроса
     * @return новая заметка
     */
    public Note create(Note note) {
        return noteRepository.save(note);
    }

    /**
     * Обнорвление заметки в БД
     *
     * @param note заметка из запроса
     * @return обновленная заметка
     */
    public Note update(Note note) {
        Note updNote = getById(note.getId());
        updNote.setTitle(note.getTitle());
        updNote.setDescription(note.getDescription());
        return noteRepository.save(updNote);
    }

    /**
     * Удаление заметки из БД
     *
     * @param id уникальный идентификатор заметки
     */
    public void delete(long id) {
        noteRepository.deleteById(id);
    }

}
