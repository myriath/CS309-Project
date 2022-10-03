package com.example.cs309android.models.USDA.queries;

import static com.example.cs309android.models.USDA.Constants.API_KEY;

import com.example.cs309android.models.GetRequestURL;
import com.example.cs309android.models.USDA.Constants;
import com.example.cs309android.models.gson.GetRequest;

/**
 * JSON for request body of 'foods' POST request. Retrieves a list of food items by a list of up to
 * 20 FDC IDs. Optional format and nutrients can be specified. Invalid FDC ID's or ones that are not
 * found are omitted and an empty set is returned if there are no matches.
 * <p>
 * From https://app.swaggerhub.com/apis/fdcnal/food-data_central_api/1.0.1#/FoodsCriteria
 */
public class FoodsCriteria extends GetRequest {
    private final Integer[] fdcIds;
    private final String format;
    private final Integer[] nutrients;
    private int index = 0;

    public FoodsCriteria(Integer[] fdcIds, String format, Integer[] nutrients) {
        this.fdcIds = fdcIds;
        this.format = format;
        this.nutrients = nutrients;
    }

    public FoodsCriteria(Integer[] fdcIds, Constants.Format format, Integer[] nutrients) {
        this(fdcIds, format.getValue(), nutrients);
    }

    public FoodsCriteria(int fdcId, Constants.Format format, Integer[] nutrients) {
        this(new Integer[] {fdcId}, format.getValue(), nutrients);
    }

    public Integer[] getFdcIds() {
        return fdcIds;
    }

    public String getFormat() {
        return format;
    }

    public Integer[] getNutrients() {
        return nutrients;
    }

    public FoodsCriteria setIndex(int index) {
        this.index = index;
        return this;
    }

    @Override
    public String getURL() {
        if (index == -1) return new GetRequestURL(Constants.API_URL_FOODS_ENDPOINT)
                .addArray("fdcId", fdcIds, index)
                .addParam("format", format)
                .addArray("nutrients", nutrients)
                .addParam("api_key", API_KEY)
                .toString();
        return new GetRequestURL(Constants.API_URL_FOOD_ENDPOINT + fdcIds[0])
                .addArray("fdcId", fdcIds, index)
                .addParam("format", format)
                .addArray("nutrients", nutrients)
                .addParam("api_key", API_KEY)
                .toString();
    }
}
