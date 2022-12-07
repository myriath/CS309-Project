package com.example.cs309android.models.api.request.nutrition;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.models.FoodLogItem;
import com.example.cs309android.models.api.models.SimpleFoodItem;
import com.example.cs309android.models.api.request.abstraction.PostRequest;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

public class AddFoodLogRequest extends PostRequest {
    /**
     * Authentication token
     */
    private final String token;

    /**
     * Food log item to add
     */
    @Expose
    private final FoodLogItem foodLogItem;

    /**
     * Public constructor
     *
     * @param token       Authentication token
     * @param foodLogItem Food log item to add
     */
    public AddFoodLogRequest(SimpleFoodItem foodLogItem, String token) {
        super(new ParameterizedRequestURL(Constants.Urls.FoodLog.ADD_FOOD_LOG_URL)
                .addPathVar(token)
                .toString());
        this.token = token;

        this.foodLogItem = new FoodLogItem(foodLogItem);
    }

    /**
     * Getter for the authentication token
     *
     * @return authentication token
     */
    public String getToken() {
        return token;
    }

    /**
     * Getter for the food log item
     *
     * @return food log item
     */
    public FoodLogItem getFoodLogItem() {
        return foodLogItem;
    }
}
