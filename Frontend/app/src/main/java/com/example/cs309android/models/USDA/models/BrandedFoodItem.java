package com.example.cs309android.models.USDA.models;

import com.google.gson.annotations.Expose;

/**
 * FDC Branded Food Item model
 *
 * @author Mitch Hudson
 */
public class BrandedFoodItem {
    /**
     * FDC id
     */
    @Expose
    private final int fdcId;
    /**
     * Date made available
     */
    @Expose
    private final String availableDate;
    /**
     * Brand name that owns this item
     */
    @Expose
    private final String brandOwner;
    /**
     * Data source
     */
    @Expose
    private final String dataSource;
    /**
     * Data type
     */
    @Expose
    private final String dataType;
    /**
     * Description / name
     */
    @Expose
    private final String description;
    /**
     * Food class
     */
    @Expose
    private final String foodClass;
    /**
     * Unknown
     */
    @Expose
    private final String gtinUpc;
    /**
     * Household serving size full text
     */
    @Expose
    private final String householdServingFullText;
    /**
     * Ingredients label
     */
    @Expose
    private final String ingredients;
    /**
     * Date last modified
     */
    @Expose
    private final String modifiedDate;
    /**
     * Publication date
     */
    @Expose
    private final String publicationDate;
    /**
     * Serving size
     */
    @Expose
    private final double servingSize;
    /**
     * Serving size unit
     */
    @Expose
    private final String servingSizeUnit;
    /**
     * Preparation state code
     */
    @Expose
    private final String preparationStateCode;
    /**
     * Food category
     */
    @Expose
    private final String brandedFoodCategory;
    /**
     * Trade channel
     */
    @Expose
    private final String[] tradeChannel;
    /**
     * GPC class code
     */
    @Expose
    private final int gpcClassCode;
    /**
     * Array of food nutrients
     */
    @Expose
    private final FoodNutrient[] foodNutrients;
    /**
     * Food update log
     */
    @Expose
    private final FoodUpdateLog[] foodUpdateLog;
    /**
     * Label nutrients
     */
    @Expose
    private final LabelNutrients labelNutrients;

    /**
     * Public constructor
     *
     * @param fdcId                    fdc id
     * @param availableDate            date made available
     * @param brandOwner               brand that owns this item
     * @param dataSource               data source
     * @param dataType                 data type
     * @param description              description / name
     * @param foodClass                food class
     * @param gtinUpc                  unknown
     * @param householdServingFullText household serving size
     * @param ingredients              ingredient list
     * @param modifiedDate             date last modified
     * @param publicationDate          publication date
     * @param servingSize              serving size
     * @param servingSizeUnit          serving size unit
     * @param preparationStateCode     preparation state code
     * @param brandedFoodCategory      food category
     * @param tradeChannel             trade channel
     * @param gpcClassCode             GPC class code
     * @param foodNutrients            food nutrients
     * @param foodUpdateLog            food update log
     * @param labelNutrients           label nutrients
     */
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

    /**
     * Getter for the fdc id
     *
     * @return fdc id
     */
    public int getFdcId() {
        return fdcId;
    }

    /**
     * Getter for the availability date
     *
     * @return availability date
     */
    public String getAvailableDate() {
        return availableDate;
    }

    /**
     * Getter for the brand owner
     *
     * @return brand owner
     */
    public String getBrandOwner() {
        return brandOwner;
    }

    /**
     * Getter for the data source
     *
     * @return data source
     */
    public String getDataSource() {
        return dataSource;
    }

    /**
     * Getter for the data type
     *
     * @return data type
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Getter for the description
     *
     * @return description / name
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the food class
     *
     * @return food class
     */
    public String getFoodClass() {
        return foodClass;
    }

    /**
     * Getter for the gtin upc?
     *
     * @return upc?
     */
    public String getGtinUpc() {
        return gtinUpc;
    }

    /**
     * Getter for the household serving size
     *
     * @return household serving size
     */
    public String getHouseholdServingFullText() {
        return householdServingFullText;
    }

    /**
     * Getter for the ingredients
     *
     * @return ingredients
     */
    public String getIngredients() {
        return ingredients;
    }

    /**
     * Getter for the date last modified
     *
     * @return date last modified
     */
    public String getModifiedDate() {
        return modifiedDate;
    }

    /**
     * Getter for the publication date
     *
     * @return publication date
     */
    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * Getter for the serving size
     *
     * @return serving size
     */
    public double getServingSize() {
        return servingSize;
    }

    /**
     * Getter for the serving size unit
     *
     * @return serving size unit
     */
    public String getServingSizeUnit() {
        return servingSizeUnit;
    }

    /**
     * Getter for the preparation state code
     *
     * @return preparation state code
     */
    public String getPreparationStateCode() {
        return preparationStateCode;
    }

    /**
     * Getter for the branded food category
     *
     * @return food category
     */
    public String getBrandedFoodCategory() {
        return brandedFoodCategory;
    }

    /**
     * Getter for the trade channels
     *
     * @return trade channels
     */
    public String[] getTradeChannel() {
        return tradeChannel;
    }

    /**
     * Getter for the GPC class code
     *
     * @return GPC class code
     */
    public int getGpcClassCode() {
        return gpcClassCode;
    }

    /**
     * Getter for the food nutrients
     *
     * @return nutrients
     */
    public FoodNutrient[] getFoodNutrients() {
        return foodNutrients;
    }

    /**
     * Getter for the food update log
     *
     * @return update log
     */
    public FoodUpdateLog[] getFoodUpdateLog() {
        return foodUpdateLog;
    }

    /**
     * Getter for the label nutrients
     *
     * @return label nutrients
     */
    public LabelNutrients getLabelNutrients() {
        return labelNutrients;
    }

    /**
     * Label nutrients representing the nutrients reported on a food item's nutrition label
     *
     * @author Mitch Hudson
     */
    public static class LabelNutrients {
        /**
         * Fat nutrient
         */
        @Expose
        private final Nutrient fat;
        /**
         * Saturated fat nutrient
         */
        @Expose
        private final Nutrient saturatedFat;
        /**
         * Trans fat nutrient
         */
        @Expose
        private final Nutrient transFat;
        /**
         * Cholesterol nutrient
         */
        @Expose
        private final Nutrient cholesterol;
        /**
         * Sodium nutrient
         */
        @Expose
        private final Nutrient sodium;
        /**
         * Carbohydrates nutrient
         */
        @Expose
        private final Nutrient carbohydrates;
        /**
         * Fiber nutrient
         */
        @Expose
        private final Nutrient fiber;
        /**
         * Sugars nutrient
         */
        @Expose
        private final Nutrient sugars;
        /**
         * Protein nutrient
         */
        @Expose
        private final Nutrient protein;
        /**
         * Calcium nutrient
         */
        @Expose
        private final Nutrient calcium;
        /**
         * Iron nutrient
         */
        @Expose
        private final Nutrient iron;
        /**
         * Potassium nutrient
         */
        @Expose
        private final Nutrient potassium;
        /**
         * Calories nutrient
         */
        @Expose
        private final Nutrient calories;

        /**
         * Public constructor
         *
         * @param fat           fat
         * @param saturatedFat  saturated fat
         * @param transFat      trans fat
         * @param cholesterol   cholesterol
         * @param sodium        sodium
         * @param carbohydrates carbohydrates
         * @param fiber         fiber
         * @param sugars        sugars
         * @param protein       protein
         * @param calcium       calcium
         * @param iron          iron
         * @param potassium     potassium
         * @param calories      calories
         */
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

        /**
         * Getter for the fat
         *
         * @return fat
         */
        public Nutrient getFat() {
            return fat;
        }

        /**
         * Getter for the saturated fat
         *
         * @return saturated fat
         */
        public Nutrient getSaturatedFat() {
            return saturatedFat;
        }

        /**
         * Getter for the trans fat
         *
         * @return trans fat
         */
        public Nutrient getTransFat() {
            return transFat;
        }

        /**
         * Getter for the cholesterol
         *
         * @return cholesterol
         */
        public Nutrient getCholesterol() {
            return cholesterol;
        }

        /**
         * Getter for the sodium
         *
         * @return sodium
         */
        public Nutrient getSodium() {
            return sodium;
        }

        /**
         * Getter for the carbohydrates
         *
         * @return carbohydrates
         */
        public Nutrient getCarbohydrates() {
            return carbohydrates;
        }

        /**
         * Getter for the fiber
         *
         * @return fiber
         */
        public Nutrient getFiber() {
            return fiber;
        }

        /**
         * Getter for the sugars
         *
         * @return sugars
         */
        public Nutrient getSugars() {
            return sugars;
        }

        /**
         * Getter for the protein
         *
         * @return protein
         */
        public Nutrient getProtein() {
            return protein;
        }

        /**
         * Getter for the calcium
         *
         * @return calcium
         */
        public Nutrient getCalcium() {
            return calcium;
        }

        /**
         * Getter for the iron
         *
         * @return iron
         */
        public Nutrient getIron() {
            return iron;
        }

        /**
         * Getter for the potassium
         *
         * @return potassium
         */
        public Nutrient getPotassium() {
            return potassium;
        }

        /**
         * Getter for the calories
         *
         * @return calories
         */
        public Nutrient getCalories() {
            return calories;
        }

        /**
         * Nutrient class representing the data for each nutrient
         *
         * @author Mitch Hudson
         */
        public static class Nutrient {
            /**
             * Value of the nutrient
             */
            @Expose
            private final float value;

            /**
             * Public constructor
             *
             * @param value value of the nutrient
             */
            public Nutrient(float value) {
                this.value = value;
            }

            /**
             * Getter for the value
             *
             * @return value
             */
            public Float getValue() {
                return value;
            }
        }
    }
}
