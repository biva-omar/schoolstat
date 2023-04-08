package com.school.schoolstat.models.dto.requests;

import lombok.Data;

import java.io.Serializable;

@Data
public class SchoolRequestDto implements Serializable {
    private String label;
    private String teachingOrder;
    private Long examSubCenterId;
}
