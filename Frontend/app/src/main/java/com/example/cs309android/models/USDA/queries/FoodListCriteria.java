package com.example.cs309android.models.USDA.queries;

import androidx.annotation.NonNull;

import com.example.cs309android.models.GetRequestURL;
import com.example.cs309android.models.USDA.Constants;

/**
 * JSON for request body of 'list' POST request
 * <p>
 * From https://app.swaggerhub.com/apis/fdcnal/food-data_central_api/1.0.1#/FoodListCriteria
 */
public class FoodListCriteria {
    private final String[] dataType;
    private final Integer pageSize;
    private final Integer pageNumber;
    private final String sortBy;
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

    @NonNull
    @Override
    public String toString() {
        return new GetRequestURL()
                .addArray("dataType", dataType)
                .addParam("pageSize", pageSize)
                .addParam("pageNumber", pageNumber)
                .addParam("sortBy", sortBy)
                .addParam("sortOrder", sortOrder)
                .toString();
    }
}
