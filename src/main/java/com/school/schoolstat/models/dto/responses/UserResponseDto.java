package com.school.schoolstat.models.dto.responses;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponseDto implements Serializable {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;
}
