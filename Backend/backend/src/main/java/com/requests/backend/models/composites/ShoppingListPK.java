package com.requests.backend.models.composites;

import java.io.Serializable;

public class ShoppingListPK implements Serializable {
    protected String username;
    protected int id;

    public ShoppingListPK() {}

    public ShoppingListPK(String username, int id) {
        this.username = username;
        this.id = id;
    }


    // equals, hashCode
}
