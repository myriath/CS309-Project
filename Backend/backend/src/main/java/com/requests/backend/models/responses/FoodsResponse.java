package com.requests.backend.models.responses;

import com.google.gson.annotations.Expose;
import com.requests.backend.models.CustomFood;
import com.requests.backend.models.responses.ResultResponse;

public class FoodsResponse {
    @Expose
    private CustomFood[] items;

    public FoodsResponse() {
    }

    public FoodsResponse(CustomFood[] items) {
        this.items = items;
    }

    public CustomFood[] getItems() {
        return items;
    }

    public void setItems(CustomFood[] items) {
        this.items = items;
    }
}
