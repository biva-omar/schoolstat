package com.school.schoolstat.controllers;

import com.school.schoolstat.models.dto.requests.NoteRequestDto;
import com.school.schoolstat.models.dto.responses.NoteResponseDto;
import com.school.schoolstat.models.entities.Note;
import com.school.schoolstat.services.interfaces.NoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody NoteRequestDto noteRequest){
        Note note = modelMapper.map(noteRequest, Note.class);

        return ResponseEntity.ok(
                modelMapper.map(noteService.createNote(note), NoteResponseDto.class)
        );
    }

    @GetMapping("/")
    public ResponseEntity<?> retrieve(){
        List<Note> notes = noteService.retrieveNote();

        return ResponseEntity.ok(
                notes.stream().map(
                        note -> modelMapper.map(note, NoteResponseDto.class)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve2(@PathVariable("id") Note note){
        return ResponseEntity.ok(
                modelMapper.map(note, NoteResponseDto.class)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Note id, @RequestBody NoteRequestDto noteRequest){
        Note note = modelMapper.map(noteRequest, Note.class);
        note.setId(id.getId());

        return ResponseEntity.ok(
                modelMapper.map(noteService.updateNote(note), NoteResponseDto.class)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Note id){
        noteService.deleteNote(id);
        return ResponseEntity.ok(
                "deleted successful"
        );
    }
}
