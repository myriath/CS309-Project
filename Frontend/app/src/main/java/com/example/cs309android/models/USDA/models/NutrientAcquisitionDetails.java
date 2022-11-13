package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class NutrientAcquisitionDetails {
    @Expose
    private final int sampleUnitId;
    @Expose
    private final String purchaseDate;
    @Expose
    private final String storeCity;
    @Expose
    private final String storeState;

    public NutrientAcquisitionDetails(int sampleUnitId, String purchaseDate, String storeCity, String storeState) {
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
