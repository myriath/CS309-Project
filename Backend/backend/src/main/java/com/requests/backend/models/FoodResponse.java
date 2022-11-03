package com.requests.backend.models;

import com.requests.backend.models.responses.ResultResponse;

public class FoodResponse extends ResultResponse {
    private CustomFood item;

    public FoodResponse() {
    }

    public FoodResponse(CustomFood item) {
        this.item = item;
    }

    public CustomFood getItem() {
        return item;
    }

    public void setItems(CustomFood item) {
        this.item = item;
    }
}
