package com.school.schoolstat.models.dto.requests;

import com.school.schoolstat.models.dto.responses.ExamSubCenterResponseDto;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExamSubCenterRequestDto implements Serializable {
    private String label;
    private Long examCenterId;
}
