package com.requests.backend.models;

public class FoodsResponse {
    CustomFood[] items;

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
