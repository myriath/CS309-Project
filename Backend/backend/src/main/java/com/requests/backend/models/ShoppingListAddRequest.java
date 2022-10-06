package com.requests.backend.models;

public class ShoppingListAddRequest {

    private SimpleFoodItem item;

    private String token;

    public SimpleFoodItem getFoodItem() {
        return item;
    }

    public void setFoodItem(SimpleFoodItem foodItem) {
        this.item = foodItem;
    }

    public String getToken() {
        return token;
    }

    public void setAuth(String token) {
        this.token = token;
    }
}
