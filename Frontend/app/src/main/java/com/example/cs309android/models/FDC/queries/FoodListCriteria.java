package com.example.cs309android.models.FDC.queries;

import androidx.annotation.NonNull;

import com.example.cs309android.models.FDC.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

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
        ArrayList<String> args = new ArrayList<>();
        for (String type : dataType) if (type != null) args.add(String.format("dataType=%s", type));
        if (pageSize != null) args.add(String.format("pageSize=%s", pageSize));
        if (pageNumber != null) args.add(String.format("pageNumber=%s", pageNumber));
        if (sortBy != null) args.add(String.format("sortBy=%s", sortBy));
        if (sortOrder != null) args.add(String.format("sortOrder=%s", sortOrder));

        try {
            return URLEncoder.encode(String.join("&", args), "utf-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
