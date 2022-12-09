package com.requests.backend.models.responses;

import com.google.gson.annotations.Expose;

public class ShoppingAddResponse extends ResultResponse {
    @Expose
    private int id;

    public ShoppingAddResponse() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SaltResponse{" +
                "result=" + getResult() +
                ", id='" + id + '\'' +
                '}';
    }
}
