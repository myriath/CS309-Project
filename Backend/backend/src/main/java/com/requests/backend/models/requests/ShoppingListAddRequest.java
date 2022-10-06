package com.requests.backend.models.requests;

import com.requests.backend.models.AuthModel;
import com.requests.backend.models.SimpleFoodItem;

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
