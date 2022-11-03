package com.example.cs309android.models.gson.response.nutrition;

import com.example.cs309android.models.gson.models.FoodLogItem;
import com.example.cs309android.models.gson.models.SimpleFoodItem;
import com.example.cs309android.models.gson.response.GenericResponse;
import com.google.gson.annotations.Expose;

public class GetFoodLogResponse extends GenericResponse {
    /**
     * Food Log array from JSON
     */
    @Expose
    private final FoodLogItem[] foodLog;

    /**
     * Public constructor
     *
     * @param result        Result code from the request
     * @param foodLog Shopping list array
     */
    public GetFoodLogResponse(int result, FoodLogItem[] foodLog) {
        super(result);
        this.foodLog = foodLog;
    }

    /**
     * Getter for the food log array
     *
     * @return Array of simple food items.
     */
    public FoodLogItem[] getFoodLog() {
        return foodLog;
    }
}
