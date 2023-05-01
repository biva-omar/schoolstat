package com.school.schoolstat.models.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class GraphResponseDto {
    //private Long id;
    private String label;
    private Long count;


}
