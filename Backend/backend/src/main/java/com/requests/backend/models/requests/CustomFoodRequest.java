package com.requests.backend.models.requests;

import com.google.gson.annotations.Expose;
import com.requests.backend.models.CustomFood;

public class CustomFoodRequest {
    @Expose
    private CustomFood item;

    public CustomFoodRequest() {
    }

    public CustomFoodRequest(CustomFood item) {
        this.item = item;
    }

    public CustomFood getItem() {
        return item;
    }
}
