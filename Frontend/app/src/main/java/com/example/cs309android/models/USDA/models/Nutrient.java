package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class Nutrient {
    @Expose
    private final int id;
    @Expose
    private final String number;
    @Expose
    private final String name;
    @Expose
    private final int rank;
    @Expose
    private final String unitName;

    public Nutrient(int id, String number, String name, int rank, String unitName) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.rank = rank;
        this.unitName = unitName;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public String getUnitName() {
        return unitName;
    }
}
