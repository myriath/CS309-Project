package com.requests.backend.models.responses;

import com.requests.backend.models.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListGetResponse {
    private int result;
    private ArrayList<ShoppingList> shoppingList;

    public void ShoppingListGetResponse() {
        this.shoppingList = new ArrayList<ShoppingList>();
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
        this.shoppingList = (ArrayList<ShoppingList>) shoppingList;
    }
}
