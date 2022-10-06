package com.requests.backend.models;

/**
 * For use with regular login
 * Token login uses ResultResponse
 */
public class LoginResponse {

    private String token;

    private int result;

    public LoginResponse() {
    }

    public LoginResponse(String token, int responseCode) {
        this.token = token;
        this.result = responseCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }
}
