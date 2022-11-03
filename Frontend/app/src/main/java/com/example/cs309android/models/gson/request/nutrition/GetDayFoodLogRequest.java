package com.example.cs309android.models.gson.request.nutrition;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.gson.GetRequest;
import com.example.cs309android.util.Constants;

public class GetDayFoodLogRequest extends GetRequest {
    /**
     * Authentication token
     */
    private final String token;

    /**
     * Date of the food log
     */
    private final String date;

    /**
     * Public constructor
     *
     * @param token Authentication token
     * @param date  Date of the food log
     */
    public GetDayFoodLogRequest(String token, String date) {
        this.token = token;
        this.date = date;
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
     * Getter for the date
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter for the URL with token and date
     *
     * @return URL with token and date
     */
    public String getURL() {
        return new ParameterizedRequestURL(Constants.GET_FOOD_LOG_BY_DAY_URL + token)
                .addParam("date", date)
                .toString();
    }
}
