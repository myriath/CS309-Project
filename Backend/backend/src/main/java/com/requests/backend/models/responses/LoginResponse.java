package com.requests.backend.models.responses;

import com.google.gson.annotations.Expose;

public class LoginResponse extends ResultResponse {
    @Expose
    private String username;

    private int userType;

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

    public int getuserType() {
        return userType;
    }

    public void setuserType(int userType) {
        this.userType = userType;
    }
}
