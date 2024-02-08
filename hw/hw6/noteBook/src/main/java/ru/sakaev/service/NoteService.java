package ru.sakaev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sakaev.model.Note;
import ru.sakaev.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // Получение всех заметок
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Получение заметки по идентификатору
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    // Создание или обновление заметки
    public Note createOrUpdateNote(Note note) {
        return noteRepository.save(note);
    }

    // Удаление заметки по идентификатору
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
}
