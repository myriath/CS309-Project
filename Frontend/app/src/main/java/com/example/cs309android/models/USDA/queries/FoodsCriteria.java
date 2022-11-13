package com.example.cs309android.models.USDA.queries;

import static com.example.cs309android.models.USDA.Constants.API_KEY;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.USDA.Constants;
import com.example.cs309android.models.api.request.abstraction.GetRequest;
import com.google.gson.annotations.Expose;

/**
 * JSON for request body of 'foods' POST request. Retrieves a list of food items by a list of up to
 * 20 FDC IDs. Optional format and nutrients can be specified. Invalid FDC ID's or ones that are not
 * found are omitted and an empty set is returned if there are no matches.
 * <p>
 * From https://app.swaggerhub.com/apis/fdcnal/food-data_central_api/1.0.1#/FoodsCriteria
 *
 * @author Mitch Hudson
 */
public class FoodsCriteria extends GetRequest {
    /**
     * FDC ids to get details for
     */
    @Expose
    private final Integer[] fdcIds;
    /**
     * Search format
     */
    @Expose
    private final String format;
    /**
     * Nutrients to get data for
     */
    @Expose
    private final Integer[] nutrients;
    /**
     * Index value to search specific fdc id
     */
    @Expose
    private int index = 0;

    /**
     * Public constructor (Multiple fdc ids, String format)
     * @param fdcIds array of fdc ids
     * @param format search format
     * @param nutrients nutrients array
     */
    public FoodsCriteria(Integer[] fdcIds, String format, Integer[] nutrients) {
        this.fdcIds = fdcIds;
        this.format = format;
        this.nutrients = nutrients;
    }

    /**
     * Public constructor (Multiple fdc ids, Format format)
     * @param fdcIds fdc ids to get details for
     * @param format search format
     * @param nutrients nutrients to get
     */
    public FoodsCriteria(Integer[] fdcIds, Constants.Format format, Integer[] nutrients) {
        this(fdcIds, format.getValue(), nutrients);
    }

    /**
     * Public constructor (Single fdc id, Format format)
     * @param fdcId fdc id to get details for
     * @param format search format
     * @param nutrients nutrients to get
     */
    public FoodsCriteria(int fdcId, Constants.Format format, Integer[] nutrients) {
        this(new Integer[]{fdcId}, format.getValue(), nutrients);
    }

    /**
     * Getter for the fdc ids
     * @return fdc ids
     */
    public Integer[] getFdcIds() {
        return fdcIds;
    }

    /**
     * Getter for the search format
     * @return search format
     */
    public String getFormat() {
        return format;
    }

    /**
     * Getter for the nutrients
     * @return nutrients
     */
    public Integer[] getNutrients() {
        return nutrients;
    }

    /**
     * Setter for the index
     * @param index Which fdc id to get (-1 for all)
     * @return this
     */
    public FoodsCriteria setIndex(int index) {
        this.index = index;
        return this;
    }

    /**
     * Getter for the request URL
     * @return request URL
     */
    @Override
    public String getURL() {
        if (index == -1) return new ParameterizedRequestURL(Constants.API_URL_FOODS_ENDPOINT)
                .addArray("fdcId", fdcIds, index)
                .addParam("format", format)
                .addArray("nutrients", nutrients)
                .addParam("api_key", API_KEY)
                .toString();
        return new ParameterizedRequestURL(Constants.API_URL_FOOD_ENDPOINT + fdcIds[0])
                .addArray("fdcId", fdcIds, index)
                .addParam("format", format)
                .addArray("nutrients", nutrients)
                .addParam("api_key", API_KEY)
                .toString();
    }
}
