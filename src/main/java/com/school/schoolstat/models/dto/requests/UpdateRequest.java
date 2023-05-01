package com.school.schoolstat.models.dto.requests;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateRequest {
    private String username;
    private String newpassword;
    private String oldPassord;

}