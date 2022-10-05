package com.requests.backend.models;

public class ShoppingListAddRequest {

    public SimpleFoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(SimpleFoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public AuthModel getAuth() {
        return auth;
    }

    public void setAuth(AuthModel auth) {
        this.auth = auth;
    }

    private SimpleFoodItem foodItem;

    private AuthModel auth;
}
