package com.example.cs309android.models.FDC.models;

public class Nutrient {
    private final int id;
    private final String number;
    private final String name;
    private final int rank;
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
