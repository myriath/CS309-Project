package com.requests.backend.models.requests;

import com.requests.backend.models.SimpleFoodItem;

public class ShoppingListAddRequest {

    private SimpleFoodItem item;

    public ShoppingListAddRequest() {
    }

    public ShoppingListAddRequest(SimpleFoodItem item) {
        this.item = item;
    }

    public SimpleFoodItem getFoodItem() {
        return item;
    }

    public void setItem(SimpleFoodItem item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return item.toString();
    }
}
