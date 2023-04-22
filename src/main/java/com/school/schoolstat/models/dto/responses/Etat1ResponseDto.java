package com.school.schoolstat.models.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Etat1ResponseDto {
    private String sousCentre;
    private Long inscritM;
    private Long inscritF;

    private Long presentM;
    private Long presentF;
}
