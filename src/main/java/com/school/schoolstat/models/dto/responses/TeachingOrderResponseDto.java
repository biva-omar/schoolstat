package com.school.schoolstat.models.dto.responses;

import com.school.schoolstat.models.enums.TeachingOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class TeachingOrderResponseDto {
    private int id;
    private TeachingOrder label;
}
