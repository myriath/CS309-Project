package com.example.cs309android.models.FDC.queries;

import com.example.cs309android.models.FDC.Constants;

/**
 * A copy of the criteria that were used in the search.
 * <p>
 * From https://app.swaggerhub.com/apis/fdcnal/food-data_central_api/1.0.1#/FoodSearchCriteria
 */
public class FoodSearchCriteria {
    private final String[] dataType;
    private final Integer pageSize;
    private final Integer pageNumber;
    private final String sortBy;
    private final String sortOrder;
    private final String brandOwner;
    private final String[] tradeChannel;
    private final String startDate;
    private final String endDate;

    public FoodSearchCriteria(String[] dataType, Integer pageSize, Integer pageNumber, String sortBy, String sortOrder, String brandOwner, String[] tradeChannel, String startDate, String endDate) {
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

    public FoodSearchCriteria(Constants.DataType[] dataTypes, Integer pageSize, Integer pageNumber, Constants.SortBy sortBy, Constants.SortOrder sortOrder, String brandOwner, Constants.TradeChannel[] tradeChannels, String startDate, String endDate) {
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
}
