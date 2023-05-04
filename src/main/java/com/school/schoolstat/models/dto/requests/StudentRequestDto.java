package com.school.schoolstat.models.dto.requests;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StudentRequestDto implements Serializable {
    private String firstname;
    private String lastname;
    private Date birthday;
    private String birthplace;
    private String sex;
    private String tutorPhone;
    private Long schoolId;

}
