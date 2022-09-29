package com.example.cs309android.models.USDA.models;

public class NutrientAquisitionDetails {
    private final int sampleUnitId;
    private final String purchaseDate;
    private final String storeCity;
    private final String storeState;

    public NutrientAquisitionDetails(int sampleUnitId, String purchaseDate, String storeCity, String storeState) {
        this.sampleUnitId = sampleUnitId;
        this.purchaseDate = purchaseDate;
        this.storeCity = storeCity;
        this.storeState = storeState;
    }

    public int getSampleUnitId() {
        return sampleUnitId;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getStoreCity() {
        return storeCity;
    }

    public String getStoreState() {
        return storeState;
    }
}
