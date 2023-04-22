package com.school.schoolstat.models.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Etat4ResponseDto {
    private String discipline;

    private Long inscritM;
    private Long inscritF;

    private Long presentM;
    private Long presentF;

    private Long admisM;
    private Long admisF;
}
