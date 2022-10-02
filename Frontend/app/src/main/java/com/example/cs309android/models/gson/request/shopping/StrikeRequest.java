package com.example.cs309android.models.gson.request.shopping;

import com.example.cs309android.models.gson.PatchRequest;

public class StrikeRequest extends PatchRequest {
    private final int index;
    private final String token;

    public StrikeRequest(int index, String token) {
        this.index = index;
        this.token = token;
    }

    public int getIndex() {
        return index;
    }

    public String getToken() {
        return token;
    }
}
