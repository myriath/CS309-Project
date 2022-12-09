package com.requests.backend.models.requests;

import com.google.gson.annotations.Expose;

public class FoodLogGetDayRequest {
    @Expose
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
