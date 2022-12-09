package com.requests.backend.models.responses;

import com.google.gson.annotations.Expose;
import com.requests.backend.models.FoodLog;

public class FoodLogGetResponse {
    @Expose
    private int result;

    @Expose
    private FoodLog[] foodLog;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public FoodLog[] getFoodLog() {
        return foodLog;
    }

    public void setFoodLog(FoodLog[] foodLog) {
        this.foodLog = foodLog;
    }
}
