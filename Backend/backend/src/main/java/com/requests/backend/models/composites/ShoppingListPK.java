package com.requests.backend.models.composites;

import java.io.Serializable;

public class ShoppingListPK implements Serializable {
    protected String username;
    protected String description;

    public ShoppingListPK() {}

    public ShoppingListPK(String username, String description) {
        this.username = username;
        this.description = description;
    }


    // equals, hashCode
}
