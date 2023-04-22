package com.school.schoolstat.models.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Etat2ResponseDto {
    private String sousCentre;
    private Long inscrit;
    private Long present;
}
