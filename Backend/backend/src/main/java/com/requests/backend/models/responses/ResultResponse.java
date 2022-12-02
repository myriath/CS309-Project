package com.requests.backend.models.responses;

import com.google.gson.annotations.Expose;

public class ResultResponse {
    @Expose
    private int result;

    public ResultResponse() {
    }

    public ResultResponse(int responseCode) {
        this.result = responseCode;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }
}
