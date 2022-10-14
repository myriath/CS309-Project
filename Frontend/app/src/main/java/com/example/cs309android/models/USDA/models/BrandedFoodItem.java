package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

public class BrandedFoodItem {
    @Expose
    private final int fdcId;
    @Expose
    private final String availableDate;
    @Expose
    private final String brandOwner;
    @Expose
    private final String dataSource;
    @Expose
    private final String dataType;
    @Expose
    private final String description;
    @Expose
    private final String foodClass;
    @Expose
    private final String gtinUpc;
    @Expose
    private final String householdServingFullText;
    @Expose
    private final String ingredients;
    @Expose
    private final String modifiedDate;
    @Expose
    private final String publicationDate;
    @Expose
    private final double servingSize;
    @Expose
    private final String servingSizeUnit;
    @Expose
    private final String preparationStateCode;
    @Expose
    private final String brandedFoodCategory;
    @Expose
    private final String[] tradeChannel;
    @Expose
    private final int gpcClassCode;
    @Expose
    private final FoodNutrient[] foodNutrients;
    @Expose
    private final FoodUpdateLog[] foodUpdateLog;
    @Expose
    private final LabelNutrients labelNutrients;

    public BrandedFoodItem(int fdcId, String availableDate, String brandOwner, String dataSource, String dataType, String description, String foodClass, String gtinUpc, String householdServingFullText, String ingredients, String modifiedDate, String publicationDate, double servingSize, String servingSizeUnit, String preparationStateCode, String brandedFoodCategory, String[] tradeChannel, int gpcClassCode, FoodNutrient[] foodNutrients, FoodUpdateLog[] foodUpdateLog, LabelNutrients labelNutrients) {
        this.fdcId = fdcId;
        this.availableDate = availableDate;
        this.brandOwner = brandOwner;
        this.dataSource = dataSource;
        this.dataType = dataType;
        this.description = description;
        this.foodClass = foodClass;
        this.gtinUpc = gtinUpc;
        this.householdServingFullText = householdServingFullText;
        this.ingredients = ingredients;
        this.modifiedDate = modifiedDate;
        this.publicationDate = publicationDate;
        this.servingSize = servingSize;
        this.servingSizeUnit = servingSizeUnit;
        this.preparationStateCode = preparationStateCode;
        this.brandedFoodCategory = brandedFoodCategory;
        this.tradeChannel = tradeChannel;
        this.gpcClassCode = gpcClassCode;
        this.foodNutrients = foodNutrients;
        this.foodUpdateLog = foodUpdateLog;
        this.labelNutrients = labelNutrients;
    }

    public int getFdcId() {
        return fdcId;
    }

    public String getAvailableDate() {
        return availableDate;
    }

    public String getBrandOwner() {
        return brandOwner;
    }

    public String getDataSource() {
        return dataSource;
    }

    public String getDataType() {
        return dataType;
    }

    public String getDescription() {
        return description;
    }

    public String getFoodClass() {
        return foodClass;
    }

    public String getGtinUpc() {
        return gtinUpc;
    }

    public String getHouseholdServingFullText() {
        return householdServingFullText;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public double getServingSize() {
        return servingSize;
    }

    public String getServingSizeUnit() {
        return servingSizeUnit;
    }

    public String getPreparationStateCode() {
        return preparationStateCode;
    }

    public String getBrandedFoodCategory() {
        return brandedFoodCategory;
    }

    public String[] getTradeChannel() {
        return tradeChannel;
    }

    public int getGpcClassCode() {
        return gpcClassCode;
    }

    public FoodNutrient[] getFoodNutrients() {
        return foodNutrients;
    }

    public FoodUpdateLog[] getFoodUpdateLog() {
        return foodUpdateLog;
    }

    public LabelNutrients getLabelNutrients() {
        return labelNutrients;
    }

    public static class LabelNutrients {
        private final Nutrient fat;
        private final Nutrient saturatedFat;
        private final Nutrient transFat;
        private final Nutrient cholesterol;
        private final Nutrient sodium;
        private final Nutrient carbohydrates;
        private final Nutrient fiber;
        private final Nutrient sugars;
        private final Nutrient protein;
        private final Nutrient calcium;
        private final Nutrient iron;
        private final Nutrient potassium;
        private final Nutrient calories;

        public LabelNutrients(Nutrient fat, Nutrient saturatedFat, Nutrient transFat, Nutrient cholesterol, Nutrient sodium, Nutrient carbohydrates, Nutrient fiber, Nutrient sugars, Nutrient protein, Nutrient calcium, Nutrient iron, Nutrient potassium, Nutrient calories) {
            this.fat = fat;
            this.saturatedFat = saturatedFat;
            this.transFat = transFat;
            this.cholesterol = cholesterol;
            this.sodium = sodium;
            this.carbohydrates = carbohydrates;
            this.fiber = fiber;
            this.sugars = sugars;
            this.protein = protein;
            this.calcium = calcium;
            this.iron = iron;
            this.potassium = potassium;
            this.calories = calories;
        }

        public Nutrient getFat() {
            return fat;
        }

        public Nutrient getSaturatedFat() {
            return saturatedFat;
        }

        public Nutrient getTransFat() {
            return transFat;
        }

        public Nutrient getCholesterol() {
            return cholesterol;
        }

        public Nutrient getSodium() {
            return sodium;
        }

        public Nutrient getCarbohydrates() {
            return carbohydrates;
        }

        public Nutrient getFiber() {
            return fiber;
        }

        public Nutrient getSugars() {
            return sugars;
        }

        public Nutrient getProtein() {
            return protein;
        }

        public Nutrient getCalcium() {
            return calcium;
        }

        public Nutrient getIron() {
            return iron;
        }

        public Nutrient getPotassium() {
            return potassium;
        }

        public Nutrient getCalories() {
            return calories;
        }

        public static class Nutrient {
            private final float value;

            public Nutrient(float value) {
                this.value = value;
            }

            public Float getValue() {
                return value;
            }
        }
    }
}
