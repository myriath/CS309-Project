package com.requests.backend.models;

import com.requests.backend.models.responses.ResultResponse;

public class FoodsResponse {
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
