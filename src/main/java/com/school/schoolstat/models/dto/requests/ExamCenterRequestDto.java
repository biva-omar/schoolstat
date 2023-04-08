package com.school.schoolstat.models.dto.requests;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExamCenterRequestDto implements Serializable {
    private String label;
}
