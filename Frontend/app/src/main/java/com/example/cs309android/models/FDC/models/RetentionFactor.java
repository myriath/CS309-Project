package com.example.cs309android.models.FDC.models;

public class RetentionFactor {
    private final int id;
    private final int code;
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
