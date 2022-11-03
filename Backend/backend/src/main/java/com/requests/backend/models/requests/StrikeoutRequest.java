package com.requests.backend.models.requests;

import com.requests.backend.models.AuthModel;

public class StrikeoutRequest {
    private String description;

    public String getItemName() {
        return description;
    }

    public void setItemName(String itemName) {
        this.description = itemName;
    }
}
