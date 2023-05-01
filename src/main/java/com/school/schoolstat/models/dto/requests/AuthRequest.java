package com.school.schoolstat.models.dto.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor @NoArgsConstructor
public class AuthRequest implements Serializable {

    @NotEmpty
    private String username;

    @NotEmpty
    private String credential;

    private boolean enable;

    public AuthRequest(String username, String credential) {
        this.username = username;
        this.credential = credential;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getUsername() {
        return username;
    }

    public String getCredential() {
        return credential;
    }

    public boolean isEnable() {
        return enable;
    }
}