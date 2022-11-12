package com.example.cs309android.models.api.request.nutrition;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;
import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

/**
 * Request for getting a day's food log for a user
 *
 * @author Travis Massner
 */
public class GetDayFoodLogRequest extends GetRequest {
    /**
     * Authentication token
     */
    private final String token;

    /**
     * Date of the food log
     */
    @Expose
    private final String date;

    /**
     * Public constructor
     * @param token Authentication token
     * @param date  Date of the food log
     */
    public GetDayFoodLogRequest(String date, String token) {
        this.token = token;
        this.date = date;
    }

    /**
     * Getter for the authentication token
     * @return authentication token
     */
    public String getToken() {
        return token;
    }

    /**
     * Getter for the date
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter for the URL with token and date
     * @return URL with token and date
     */
    public String getURL() {
        return new ParameterizedRequestURL(Constants.GET_FOOD_LOG_BY_DAY_URL)
                .addPathVar(token)
                .addParam("date", date)
                .toString();
    }
}
