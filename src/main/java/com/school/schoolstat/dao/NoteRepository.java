package com.school.schoolstat.dao;

import com.school.schoolstat.models.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}