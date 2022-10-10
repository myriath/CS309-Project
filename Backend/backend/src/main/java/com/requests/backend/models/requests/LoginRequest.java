package com.requests.backend.models.requests;

public class LoginRequest {
    private String username;

    private String pHash;

    public LoginRequest(String username, String pHash) {
        this.username = username;
        this.pHash = pHash;
    }

    public String getUsername() {
        return username;
    }

    public String getPHash() {
        return pHash;
    }
}
