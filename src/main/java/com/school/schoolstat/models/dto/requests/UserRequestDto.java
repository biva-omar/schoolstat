package com.school.schoolstat.models.dto.requests;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRequestDto implements Serializable {
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String password;
}
