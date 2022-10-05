package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class NutrientAnalysisDetails {
    @Expose
    private final int subSampleId;
    @Expose
    private final float amount;
    @Expose
    private final int nutrientId;
    @Expose
    private final String labMethodDescription;
    @Expose
    private final String labMethodOriginalDescription;
    @Expose
    private final String labMethodLink;
    @Expose
    private final String labMethodTechnique;
    @Expose
    private final NutrientAquisitionDetails[] nutrientAquisitionDetails;

    public NutrientAnalysisDetails(int subSampleId, float amount, int nutrientId, String labMethodDescription, String labMethodOriginalDescription, String labMethodLink, String labMethodTechnique, NutrientAquisitionDetails[] nutrientAquisitionDetails) {
        this.subSampleId = subSampleId;
        this.amount = amount;
        this.nutrientId = nutrientId;
        this.labMethodDescription = labMethodDescription;
        this.labMethodOriginalDescription = labMethodOriginalDescription;
        this.labMethodLink = labMethodLink;
        this.labMethodTechnique = labMethodTechnique;
        this.nutrientAquisitionDetails = nutrientAquisitionDetails;
    }

    public int getSubSampleId() {
        return subSampleId;
    }

    public float getAmount() {
        return amount;
    }

    public int getNutrientId() {
        return nutrientId;
    }

    public String getLabMethodDescription() {
        return labMethodDescription;
    }

    public String getLabMethodOriginalDescription() {
        return labMethodOriginalDescription;
    }

    public String getLabMethodLink() {
        return labMethodLink;
    }

    public String getLabMethodTechnique() {
        return labMethodTechnique;
    }

    public NutrientAquisitionDetails[] getNutrientAquisitionDetails() {
        return nutrientAquisitionDetails;
    }
}
