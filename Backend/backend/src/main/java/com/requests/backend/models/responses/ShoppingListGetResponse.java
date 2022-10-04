package com.requests.backend.models.responses;

import com.requests.backend.models.ShoppingList;

import java.util.List;

public class ShoppingListGetResponse {
    private int result;
    private List<ShoppingList> shoppingList;

    public void ShoppingListGetResponse() {
        this.shoppingList = null;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<ShoppingList> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<ShoppingList> shoppingList) {
        this.shoppingList = shoppingList;
    }
}
