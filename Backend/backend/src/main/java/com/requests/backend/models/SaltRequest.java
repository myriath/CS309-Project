package com.requests.backend.models;

public class SaltRequest {
    private String username;

    public SaltRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
