package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Food Attribute class
 *
 * @author Mitch Hudson
 */
public class FoodAttribute {
    /**
     * ID for the attribute
     */
    @Expose
    private final int id;
    /**
     * Sequence number
     */
    @Expose
    private final int sequenceNumber;
    /**
     * Value of the attribute
     */
    @Expose
    private final String value;
    /**
     * Food attribute type
     */
    @Expose
    private final FoodAttributeType FoodAttributeType;

    /**
     * Public constructor
     *
     * @param id                attribute id
     * @param sequenceNumber    sequence number
     * @param value             attribute value
     * @param foodAttributeType attribute type
     */
    public FoodAttribute(int id, int sequenceNumber, String value, FoodAttribute.FoodAttributeType foodAttributeType) {
        this.id = id;
        this.sequenceNumber = sequenceNumber;
        this.value = value;
        FoodAttributeType = foodAttributeType;
    }

    /**
     * Getter for the id
     *
     * @return attribute id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for the sequence number
     *
     * @return sequence number
     */
    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Getter for the attribute value
     *
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Getter for the attribute type
     *
     * @return attribute type
     */
    public FoodAttribute.FoodAttributeType getFoodAttributeType() {
        return FoodAttributeType;
    }

    /**
     * Food attribute type class
     *
     * @author Mitch Hudson
     */
    public static class FoodAttributeType {
        /**
         * Type ID
         */
        private final int id;
        /**
         * Type name
         */
        private final String name;
        /**
         * Type description
         */
        private final String description;

        /**
         * Public constructor
         *
         * @param id          type id
         * @param name        type name
         * @param description type description
         */
        public FoodAttributeType(int id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        /**
         * Getter for the id
         *
         * @return id
         */
        public int getId() {
            return id;
        }

        /**
         * Getter for the name
         *
         * @return name
         */
        public String getName() {
            return name;
        }

        /**
         * Getter for the description
         *
         * @return description
         */
        public String getDescription() {
            return description;
        }
    }
}
