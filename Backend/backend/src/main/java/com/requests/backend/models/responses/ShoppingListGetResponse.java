package com.requests.backend.models.responses;

import com.requests.backend.models.ShoppingList;

public class ShoppingListGetResponse {

    private int result;

    private ShoppingList[] shoppingList;

    public ShoppingListGetResponse() {
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ShoppingList[] getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList[] shoppingList) {
        this.shoppingList = shoppingList;
    }
}
