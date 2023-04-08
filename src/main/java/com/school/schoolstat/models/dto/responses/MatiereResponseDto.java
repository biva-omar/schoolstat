package com.school.schoolstat.models.dto.responses;

import lombok.Data;

import java.io.Serializable;

@Data
public class MatiereResponseDto implements Serializable {
    private Long id;
    private String label;
}
