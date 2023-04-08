package com.school.schoolstat.services.impelements;

import com.school.schoolstat.dao.NoteRepository;
import com.school.schoolstat.models.entities.Note;
import com.school.schoolstat.services.interfaces.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public Note retrieveNote(Long id) {
        return noteRepository.findById(id).get();
    }

    @Override
    public List<Note> retrieveNote() {
        return noteRepository.findAll();
    }

    @Override
    public Note updateNote(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void deleteNote(Note note) {
        noteRepository.delete(note);
    }
}
