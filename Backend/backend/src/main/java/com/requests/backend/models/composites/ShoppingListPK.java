package com.requests.backend.models.composites;

import java.io.Serializable;

public class ShoppingListPK implements Serializable {
    protected String username;
    protected Integer fdcId;

    public ShoppingListPK() {}

    public ShoppingListPK(String username, Integer fdcId) {
        this.username = username;
        this.fdcId = fdcId;
    }
    // equals, hashCode
}
