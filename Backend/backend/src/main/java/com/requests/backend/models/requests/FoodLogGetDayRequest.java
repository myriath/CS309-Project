package com.requests.backend.models.requests;

import com.google.gson.annotations.Expose;

import java.sql.Date;

public class FoodLogGetDayRequest {
    @Expose
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
