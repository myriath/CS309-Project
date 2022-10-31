package com.example.cs309android.models.USDA.queries;

import static com.example.cs309android.models.USDA.Constants.API_KEY;
import static com.example.cs309android.models.USDA.Constants.API_URL_LIST_ENDPOINT;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.USDA.Constants;
import com.example.cs309android.models.gson.request.abstraction.GetRequest;
import com.google.gson.annotations.Expose;

/**
 * JSON for request body of 'list' POST request
 * <p>
 * From https://app.swaggerhub.com/apis/fdcnal/food-data_central_api/1.0.1#/FoodListCriteria
 */
public class FoodListCriteria extends GetRequest {
    @Expose
    private final String[] dataType;
    @Expose
    private final Integer pageSize;
    @Expose
    private final Integer pageNumber;
    @Expose
    private final String sortBy;
    @Expose
    private final String sortOrder;

    public FoodListCriteria(String[] dataType, Integer pageSize, Integer pageNumber, String sortBy, String sortOrder) {
        this.dataType = dataType;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
    }

    public FoodListCriteria(Constants.DataType[] dataTypes, Integer pageSize, Integer pageNumber, Constants.SortBy sortBy, Constants.SortOrder sortOrder) {
        String[] dataType = new String[dataTypes.length];
        for (int i = 0; i < dataTypes.length; i++) dataType[i] = dataTypes[i].getValue();
        this.dataType = dataType;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sortBy = sortBy.getValue();
        this.sortOrder = sortOrder.getValue();
    }

    public String[] getDataType() {
        return dataType;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    @Override
    public String getURL() {
        return new ParameterizedRequestURL(API_URL_LIST_ENDPOINT)
                .addArray("dataType", dataType)
                .addParam("pageSize", pageSize)
                .addParam("pageNumber", pageNumber)
                .addParam("sortBy", sortBy)
                .addParam("sortOrder", sortOrder)
                .addParam("api_key", API_KEY)
                .toString();
    }
}
