package com.requests.backend.models.responses;

import com.google.gson.annotations.Expose;

public class AddRecipeResponse extends ResultResponse {
    @Expose
    private int rid;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }
}
