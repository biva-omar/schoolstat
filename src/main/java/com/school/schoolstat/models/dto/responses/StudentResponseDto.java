package com.school.schoolstat.models.dto.responses;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StudentResponseDto implements Serializable {
    private Long id;
    private String firstname;
    private String lastname;
    private Date birthday;
    private String sex;
    private String tutorPhone;
    private String schoolLabel;
}
