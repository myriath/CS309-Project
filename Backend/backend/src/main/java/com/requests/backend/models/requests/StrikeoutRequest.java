package com.requests.backend.models.requests;

import com.requests.backend.models.AuthModel;

public class StrikeoutRequest {
    private int id;
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
