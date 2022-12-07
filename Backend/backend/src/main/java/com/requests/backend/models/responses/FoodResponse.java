package com.requests.backend.models.responses;

import com.google.gson.annotations.Expose;
import com.requests.backend.models.CustomFood;

public class FoodResponse extends ResultResponse {
    @Expose
    private CustomFood item;

    public FoodResponse() {
    }

    public FoodResponse(CustomFood item) {
        this.item = item;
    }

    public CustomFood getItem() {
        return item;
    }

    public void setItem(CustomFood item) {
        this.item = item;
    }
}
