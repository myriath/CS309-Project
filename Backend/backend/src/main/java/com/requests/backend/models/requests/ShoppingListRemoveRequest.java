package com.requests.backend.models.requests;

import com.requests.backend.models.AuthModel;

public class ShoppingListRemoveRequest {
    private String description;

    private String token;

    public String getItemName() {
        return this.description;
    }

    public void setItemName(String itemName) {
        this.description = itemName;
    }
}
