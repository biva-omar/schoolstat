package com.school.schoolstat.models.dto.responses;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExamSubCenterResponseDto implements Serializable {
    private Long id;
    private String label;
    private Long examCenterId;
}
