package com.requests.backend.models;

public class SaltResponse {

    private int result;
    private String salt;

    public SaltResponse() {
    }

    public SaltResponse(int responseCode) {
        result = responseCode;
    }
    public SaltResponse(int responseCode, String salt) {
        result = responseCode;
        this.salt = salt;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
