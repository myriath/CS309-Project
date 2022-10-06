package com.requests.backend.models;

public class ShoppingListAddRequest {

    private SimpleFoodItem item;

    private AuthModel auth;

    public SimpleFoodItem getFoodItem() {
        return item;
    }

    public void setFoodItem(SimpleFoodItem foodItem) {
        this.item = foodItem;
    }

    public AuthModel getAuth() {
        return auth;
    }

    public void setAuth(AuthModel auth) {
        this.auth = auth;
    }
}