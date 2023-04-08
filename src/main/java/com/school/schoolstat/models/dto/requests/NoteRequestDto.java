package com.school.schoolstat.models.dto.requests;

import lombok.Data;

import java.io.Serializable;

@Data
public class NoteRequestDto implements Serializable {
    private String label;
    private String appreciation;
    private Long studentId;
    private Long matiereId;
}
