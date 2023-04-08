package com.school.schoolstat.models.dto.requests;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExamClassroomRequestDto implements Serializable {
    private String label;
    private Long examSubCenterId;
}
