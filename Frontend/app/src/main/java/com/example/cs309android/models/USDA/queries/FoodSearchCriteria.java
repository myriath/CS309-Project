package com.example.cs309android.models.USDA.queries;

import static com.example.cs309android.models.USDA.Constants.API_KEY;
import static com.example.cs309android.models.USDA.Constants.API_URL_SEARCH_ENDPOINT;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.USDA.Constants;
import com.example.cs309android.models.api.request.abstraction.GetRequest;
import com.google.gson.annotations.Expose;

/**
 * A copy of the criteria that were used in the search.
 * <p>
 * From https://app.swaggerhub.com/apis/fdcnal/food-data_central_api/1.0.1#/FoodSearchCriteria
 */
public class FoodSearchCriteria extends GetRequest {
    @Expose
    private final String query;
    @Expose
    private String[] dataType;
    @Expose
    private Integer pageSize;
    @Expose
    private Integer pageNumber;
    @Expose
    private String sortBy;
    @Expose
    private String sortOrder;
    @Expose
    private String brandOwner;
    @Expose
    private String[] tradeChannel;
    @Expose
    private String startDate;
    @Expose
    private String endDate;

    public FoodSearchCriteria(String query, String[] dataType, Integer pageSize, Integer pageNumber, String sortBy, String sortOrder, String brandOwner, String[] tradeChannel, String startDate, String endDate) {
        this.query = query;
        this.dataType = dataType;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
        this.brandOwner = brandOwner;
        this.tradeChannel = tradeChannel;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public FoodSearchCriteria(String query, Constants.DataType[] dataTypes, Integer pageSize, Integer pageNumber, Constants.SortBy sortBy, Constants.SortOrder sortOrder, String brandOwner, Constants.TradeChannel[] tradeChannels, String startDate, String endDate) {
        this.query = query;
        String[] dataType = new String[dataTypes.length];
        for (int i = 0; i < dataTypes.length; i++) dataType[i] = dataTypes[i].getValue();
        this.dataType = dataType;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.sortBy = sortBy.getValue();
        this.sortOrder = sortOrder.getValue();
        this.brandOwner = brandOwner;
        String[] tradeChannel = new String[tradeChannels.length];
        for (int i = 0; i < tradeChannels.length; i++)
            tradeChannel[i] = tradeChannels[i].getValue();
        this.tradeChannel = tradeChannel;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public FoodSearchCriteria(String query, Constants.DataType dataType) {
        this.query = query;
        this.dataType = new String[]{dataType.getValue()};
        this.pageSize = null;
        this.pageNumber = null;
        this.sortBy = null;
        this.sortOrder = null;
        this.brandOwner = null;
        this.tradeChannel = null;
        this.startDate = null;
        this.endDate = null;
    }

    public void setDataType(String[] dataType) {
        this.dataType = dataType;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setBrandOwner(String brandOwner) {
        this.brandOwner = brandOwner;
    }

    public void setTradeChannel(String[] tradeChannel) {
        this.tradeChannel = tradeChannel;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public String getBrandOwner() {
        return brandOwner;
    }

    public String[] getTradeChannel() {
        return tradeChannel;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    @Override
    public String getURL() {
        return new ParameterizedRequestURL(API_URL_SEARCH_ENDPOINT)
                .addParam("query", query)
                .addArray("dataType", dataType)
                .addParam("pageSize", pageSize)
                .addParam("pageNumber", pageNumber)
                .addParam("sortBy", sortBy)
                .addParam("sortOrder", sortOrder)
                .addParam("brandOwner", brandOwner)
                .addArray("tradeChannel", tradeChannel)
                .addParam("startDate", startDate)
                .addParam("endDate", endDate)
                .addParam("api_key", API_KEY)
                .toString();
    }
}
