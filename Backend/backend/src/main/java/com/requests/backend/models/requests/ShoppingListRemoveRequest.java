package com.requests.backend.models.requests;

import com.google.gson.annotations.Expose;

public class ShoppingListRemoveRequest {
    @Expose
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
