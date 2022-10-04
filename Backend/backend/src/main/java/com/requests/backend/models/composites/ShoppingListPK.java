package com.requests.backend.models.composites;

import java.io.Serializable;

public class ShoppingListPK implements Serializable {
    protected String username;
    protected String itemName;

    public ShoppingListPK() {}

    public ShoppingListPK(String username, String itemName) {
        this.username = username;
        this.itemName = itemName;
    }
    // equals, hashCode
}
