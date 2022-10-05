package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class RetentionFactor {
    @Expose
    private final int id;
    @Expose
    private final int code;
    @Expose
    private final String description;

    public RetentionFactor(int id, int code, String description) {
        this.id = id;
        this.code = code;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
