package com.requests.backend.models.responses;

import com.google.gson.annotations.Expose;

public class CustomFoodResponse {
    @Expose
    private int dbId;

    @Expose
    private int result;

    public CustomFoodResponse() {
    }

    public CustomFoodResponse(int dbId, int result) {
        this.dbId = dbId;
        this.result = result;
    }

    public int getDbId() {
        return dbId;
    }

    public void setDbId(int dbId) {
        this.dbId = dbId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
