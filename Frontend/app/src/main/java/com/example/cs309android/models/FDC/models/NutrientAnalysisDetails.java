package com.example.cs309android.models.FDC.models;

public class NutrientAnalysisDetails {
    private final int subSampleId;
    private final float amount;
    private final int nutrientId;
    private final String labMethodDescription;
    private final String labMethodOriginalDescription;
    private final String labMethodLink;
    private final String labMethodTechnique;
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
