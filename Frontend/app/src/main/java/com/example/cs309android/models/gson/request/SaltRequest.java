package com.example.cs309android.models.gson.request;

public class SaltRequest {
    private final String username;

    public SaltRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
