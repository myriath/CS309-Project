package com.requests.backend.models.responses;

import com.requests.backend.models.FoodLog;

public class FoodLogGetResponse {
    private int result;

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
