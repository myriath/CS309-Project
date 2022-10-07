package com.requests.backend.models.requests;

import com.requests.backend.models.AuthModel;

public class ShoppingListRemoveRequest {
    private String itemName;

    private AuthModel auth;

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public AuthModel getAuth() {
        return auth;
    }

    public void setAuth(AuthModel auth) {
        this.auth = auth;
    }
}
