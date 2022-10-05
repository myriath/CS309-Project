package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class FoodAttribute {
    @Expose
    private final int id;
    @Expose
    private final int sequenceNumber;
    @Expose
    private final String value;
    @Expose
    private final FoodAttributeType FoodAttributeType;

    public FoodAttribute(int id, int sequenceNumber, String value, FoodAttribute.FoodAttributeType foodAttributeType) {
        this.id = id;
        this.sequenceNumber = sequenceNumber;
        this.value = value;
        FoodAttributeType = foodAttributeType;
    }

    public int getId() {
        return id;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public String getValue() {
        return value;
    }

    public FoodAttribute.FoodAttributeType getFoodAttributeType() {
        return FoodAttributeType;
    }

    public static class FoodAttributeType {
        private final int id;
        private final String name;
        private final String description;

        public FoodAttributeType(int id, String name, String description) {
            this.id = id;
            this.name = name;
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }
}
