package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Nutrient Analysis Details model
 *
 * @author Mitch Hudson
 */
public class NutrientAnalysisDetails {
    /**
     * Sub sample id
     */
    @Expose
    private final int subSampleId;
    /**
     * Amount
     */
    @Expose
    private final float amount;
    /**
     * Nutrient id
     */
    @Expose
    private final int nutrientId;
    /**
     * Lab method
     */
    @Expose
    private final String labMethodDescription;
    /**
     * Lab method original description
     */
    @Expose
    private final String labMethodOriginalDescription;
    /**
     * Lab method link
     */
    @Expose
    private final String labMethodLink;
    /**
     * Lab method technique
     */
    @Expose
    private final String labMethodTechnique;
    /**
     * Nutrient acquisition details
     */
    @Expose
    private final NutrientAcquisitionDetails[] nutrientAcquisitionDetails;

    /**
     * Public constructor
     *
     * @param subSampleId                  sub sample id
     * @param amount                       amount
     * @param nutrientId                   nutrient id
     * @param labMethodDescription         lab method description
     * @param labMethodOriginalDescription lab method original description
     * @param labMethodLink                lab method link
     * @param labMethodTechnique           lab method technique
     * @param nutrientAcquisitionDetails   acquisition details
     */
    public NutrientAnalysisDetails(int subSampleId, float amount, int nutrientId, String labMethodDescription, String labMethodOriginalDescription, String labMethodLink, String labMethodTechnique, NutrientAcquisitionDetails[] nutrientAcquisitionDetails) {
        this.subSampleId = subSampleId;
        this.amount = amount;
        this.nutrientId = nutrientId;
        this.labMethodDescription = labMethodDescription;
        this.labMethodOriginalDescription = labMethodOriginalDescription;
        this.labMethodLink = labMethodLink;
        this.labMethodTechnique = labMethodTechnique;
        this.nutrientAcquisitionDetails = nutrientAcquisitionDetails;
    }

    /**
     * Getter for the sub sample id
     *
     * @return sub sample id
     */
    public int getSubSampleId() {
        return subSampleId;
    }

    /**
     * Getter for the amount
     *
     * @return amount
     */
    public float getAmount() {
        return amount;
    }

    /**
     * Getter for the nutrient id
     *
     * @return nutrient id
     */
    public int getNutrientId() {
        return nutrientId;
    }

    /**
     * Getter for the lab method description
     *
     * @return lab method description
     */
    public String getLabMethodDescription() {
        return labMethodDescription;
    }

    /**
     * Getter for the lab method original description
     *
     * @return lab method original description
     */
    public String getLabMethodOriginalDescription() {
        return labMethodOriginalDescription;
    }

    /**
     * Getter for the lab method link
     *
     * @return lab method link
     */
    public String getLabMethodLink() {
        return labMethodLink;
    }

    /**
     * Getter for the lab method technique
     *
     * @return lab method technique
     */
    public String getLabMethodTechnique() {
        return labMethodTechnique;
    }

    /**
     * Getter for the nutrient acquisition details
     *
     * @return nutrient acquisition details
     */
    public NutrientAcquisitionDetails[] getNutrientAcquisitionDetails() {
        return nutrientAcquisitionDetails;
    }
}
