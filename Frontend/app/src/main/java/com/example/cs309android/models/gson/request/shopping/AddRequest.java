package com.example.cs309android.models.gson.request.shopping;

import com.example.cs309android.models.gson.PostRequest;

public class AddRequest extends PostRequest {
    private final int fdcId;
    private final String description;
    private final String token;

    public AddRequest(int fdcId, String description, String token) {
        this.fdcId = fdcId;
        this.description = description;
        this.token = token;
    }

    public int getFdcId() {
        return fdcId;
    }

    public String getDescription() {
        return description;
    }

    public String getToken() {
        return token;
    }
}
