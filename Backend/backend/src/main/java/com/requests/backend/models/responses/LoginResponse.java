package com.requests.backend.models.responses;

public class LoginResponse extends ResultResponse {
    private String username;

    public LoginResponse() {
    }

    public LoginResponse(int responseCode) {
        super(responseCode);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
