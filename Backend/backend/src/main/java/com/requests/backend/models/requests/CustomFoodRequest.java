package com.requests.backend.models.requests;

import com.requests.backend.models.CustomFood;

public class CustomFoodRequest {
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
