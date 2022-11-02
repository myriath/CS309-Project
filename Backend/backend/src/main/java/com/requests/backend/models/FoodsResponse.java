package com.requests.backend.models;

public class FoodsResponse {
    private CustomFood[] items;

    public FoodsResponse() {
        this.items = new CustomFood[0];
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
