package com.requests.backend.models;

public class LoginTokenRequest {
    private String token;

    public LoginTokenRequest(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
