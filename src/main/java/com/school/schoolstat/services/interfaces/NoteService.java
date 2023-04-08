package com.school.schoolstat.services.interfaces;

import com.school.schoolstat.models.entities.Note;

import java.util.List;

public interface NoteService {

    public Note createNote(Note note);

    public Note retrieveNote(Long id);

    public List<Note> retrieveNote();

    public Note updateNote(Note note);

    public void deleteNote(Note note);

}
