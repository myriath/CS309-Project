package com.requests.backend.models.responses;

public class SaltResponse {

    private int result;
    private String salt;

    public SaltResponse() {
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
