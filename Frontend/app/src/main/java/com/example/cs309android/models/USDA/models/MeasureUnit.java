package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class MeasureUnit {
    @Expose
    private final int id;
    @Expose
    private final String abbreviation;
    @Expose
    private final String name;

    public MeasureUnit(int id, String abbreviation, String name) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }
}
