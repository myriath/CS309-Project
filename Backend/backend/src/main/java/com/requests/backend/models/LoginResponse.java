package com.requests.backend.models;

public class LoginResponse {

    private int result;

    public LoginResponse() {
    }

    public LoginResponse(int responseCode) {
        this.result = responseCode;
    }

    public void setResult(int result) {
        this.result = result;
    }
}