package com.requests.backend.models.requests;

import com.requests.backend.models.AuthModel;

public class ShoppingListRemoveRequest {
    private String itemName;

    // TODO: Replace with token
    private String token;

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
