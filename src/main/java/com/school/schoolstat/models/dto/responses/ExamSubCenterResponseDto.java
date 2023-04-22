package com.school.schoolstat.models.dto.responses;

import com.school.schoolstat.models.entities.ExamCenter;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExamSubCenterResponseDto implements Serializable {
    private Long id;
    private String label;
    private ExamCenter examCenter;
}
