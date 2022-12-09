package com.requests.backend.models.requests;

import com.google.gson.annotations.Expose;

public class RegisterRequest {
    @Expose
    private String username;

    @Expose
    private String email;

    @Expose
    private String pHash;

    @Expose
    private String pSalt;

    @Expose
    private String token;

    public RegisterRequest(String username, String email, String pHash, String pSalt, String token){
        this.username = username;
        this.email = email;
        this.pHash = pHash;
        this.pSalt = pSalt;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPHash() {
        return pHash;
    }

    public void setPHash(String pHash) {
        this.pHash = pHash;
    }

    public String getPSalt() {
        return pSalt;
    }

    public void setPSalt(String pSalt) {
        this.pSalt = pSalt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
