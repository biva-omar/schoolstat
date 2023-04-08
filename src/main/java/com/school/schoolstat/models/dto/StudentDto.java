package com.school.schoolstat.models.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StudentDto implements Serializable {
    private final String firstname;
    private final String lastname;
    private final Date birthday;
    private final String tutorPhone;
    private final Long schoolId;
    private final String schoolLabel;
    private final String schoolTeachingOrder;
}
