package com.example.cs309android.models.FDC.models;

public class MeasureUnit {
    private final int id;
    private final String abbreviation;
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
