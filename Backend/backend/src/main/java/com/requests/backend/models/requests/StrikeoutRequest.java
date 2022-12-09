package com.requests.backend.models.requests;

import com.google.gson.annotations.Expose;

public class StrikeoutRequest {
    @Expose
    private int id;
    @Expose
    private boolean isCustom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }
}
