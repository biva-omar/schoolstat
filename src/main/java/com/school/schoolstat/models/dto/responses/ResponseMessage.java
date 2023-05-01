package com.school.schoolstat.models.dto.responses;

import lombok.Data;

@Data
public class ResponseMessage {

    private int status;

    private String message;

    public ResponseMessage(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
