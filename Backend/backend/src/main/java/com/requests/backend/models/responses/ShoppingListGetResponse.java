package com.requests.backend.models.responses;

import com.requests.backend.models.ShoppingList;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListGetResponse {
    private int result;

    public void ShoppingListGetResponse() {
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
