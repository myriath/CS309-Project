package com.requests.backend.models.responses;

public class ResultResponse {

    private int result;

    public ResultResponse() {
    }

    public ResultResponse(int responseCode) {
        this.result = responseCode;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
