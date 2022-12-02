package com.requests.backend.models.requests;

import com.google.gson.annotations.Expose;

public class ShoppingListRemoveRequest {
    @Expose
    private int id;

    @Expose
    private boolean isCustom;

    public ShoppingListRemoveRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean custom) {
        isCustom = custom;
    }
}
