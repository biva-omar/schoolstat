package com.school.schoolstat.models.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Etat3ResponseDto {
    private String ordreEnseignement;

    private Long inscritM;
    private Long inscritF;

    private Long presentM;
    private Long presentF;

    private Long admisM;
    private Long admisF;
}
