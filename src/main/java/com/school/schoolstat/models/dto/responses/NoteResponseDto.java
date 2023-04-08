package com.school.schoolstat.models.dto.responses;

import lombok.Data;

import java.io.Serializable;

@Data
public class NoteResponseDto implements Serializable {
    private Long id;
    private String label;
    private String appreciation;
    private Long studentId;
    private Long matiereId;
}
