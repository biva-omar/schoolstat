package com.school.schoolstat.models.dto.responses;


import java.io.Serializable;

public class JwtResponse implements Serializable {

    private String username;

    private int status;

    private String sessionId;

    private String jwttoken;

    public JwtResponse() {

    }

    public JwtResponse(String username, int status, String sessionId, String jwttoken) {
        this.username = username;
        this.status = status;
        this.sessionId = sessionId;
        this.jwttoken = jwttoken;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setJwttoken(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public String getSessionId() {
        return sessionId;
    }

}

