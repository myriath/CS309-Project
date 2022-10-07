package com.requests.backend.models.requests;

import java.util.Base64;

public class RegisterRequest {
    private String username;

    private String email;

    private String pHash;

    private String pSalt;

    public RegisterRequest(String username, String email, String pHash, String pSalt){
        this.username = username;
        this.email = email;
        this.pHash = pHash;
        this.pSalt = pSalt;
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

}
