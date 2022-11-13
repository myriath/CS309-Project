package com.example.cs309android.models.USDA.queries;

import static com.example.cs309android.models.USDA.Constants.API_KEY;
import static com.example.cs309android.models.USDA.Constants.API_URL_LIST_ENDPOINT;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.USDA.Constants;
import com.example.cs309android.models.api.request.abstraction.GetRequest;
import com.google.gson.annotations.Expose;

/**
 * JSON for request body of 'list' POST request
 * <p>
 * From https://app.swaggerhub.com/apis/fdcnal/food-data_central_api/1.0.1#/FoodListCriteria
 *
 * @author Mitch Hudson
 */
public class FoodListCriteria extends GetRequest {
    /**
     * Array of data types to list
     */
    @Expose
    private final String[] dataType;
    /**
     * Page size (# of elements per page)
     */
    @Expose
    private final Integer pageSize;
    /**
     * Page number
     */
    @Expose
    private final Integer pageNumber;
    /**
     * Sort by prompt
     */
    @Expose
    private final String sortBy;
    /**
     * Sort order prompt
     */
    @Expose
    private final String sortOrder;

    /**
     * Public constructor (String data type)
     * @param dataType data types to search for
     * @param pageSize # of elements per page
     * @param pageNumber page number
     * @param sortBy sort by prompt
     * @param sortOrder sort order prompt
     */
    public FoodListCriteria(String[] dataType, Integer pageSize, Integer pageNumber, String sortBy, String sortOrder) {
        this.dataType = dataType;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
    }

    /**
     * Public constructor (DataType data types)
     * @param dataTypes data types to search for
     * @param pageSize # of elements per page
     * @param pageNumber page number
     * @param sortBy sort by prompt
     * @param sortOrder sort order prompt
     */
    public FoodListCriteria(Constants.DataType[] dataTypes, Integer pageSize, Integer pageNumber, Constants.SortBy sortBy, Constants.SortOrder sortOrder) {
        String[] dataType = new String[dataTypes.length];
        for (int i = 0; i < dataTypes.length; i++) dataType[i] = dataTypes[i].getValue();
        this.dataType = dataType;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sortBy = sortBy.getValue();
        this.sortOrder = sortOrder.getValue();
    }

    /**
     * Getter for the data type prompt
     * @return data type
     */
    public String[] getDataType() {
        return dataType;
    }

    /**
     * Getter for the page size prompt
     * @return page size
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Getter for the page number
     * @return page number
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    /**
     * Getter for the sort by prompt
     * @return sort by prompt
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * Getter for the sort order prompt
     * @return sort order prompt
     */
    public String getSortOrder() {
        return sortOrder;
    }

    /**
     * Getter for the request URL
     * @return request URL
     */
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
