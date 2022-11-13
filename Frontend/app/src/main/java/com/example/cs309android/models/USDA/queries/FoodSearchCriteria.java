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
 *
 * @author Mitch Hudson
 */
public class FoodSearchCriteria extends GetRequest {
    /**
     * Search query
     */
    @Expose
    private final String query;
    /**
     * Search data types
     */
    @Expose
    private String[] dataType;
    /**
     * Page size
     */
    @Expose
    private Integer pageSize;
    /**
     * Page number
     */
    @Expose
    private Integer pageNumber;
    /**
     * Sort by prompt
     */
    @Expose
    private String sortBy;
    /**
     * Sort order prompt
     */
    @Expose
    private String sortOrder;
    /**
     * Brand to get food items of
     */
    @Expose
    private String brandOwner;
    /**
     * Trade channel prompt
     */
    @Expose
    private String[] tradeChannel;
    /**
     * Start date
     */
    @Expose
    private String startDate;
    /**
     * End date
     */
    @Expose
    private String endDate;

    /**
     * Public constructor
     * @param query search query
     * @param dataType data types to search
     * @param pageSize page size
     * @param pageNumber page number
     * @param sortBy sort by prompt
     * @param sortOrder sort order prompt
     * @param brandOwner brand owner prompt
     * @param tradeChannel trade channels prompt
     * @param startDate start date prompt
     * @param endDate end date prompt
     */
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

    /**
     * Public constructor
     * @param query search query
     * @param dataTypes data types to search
     * @param pageSize page size
     * @param pageNumber page number
     * @param sortBy sort by prompt
     * @param sortOrder sort order prompt
     * @param brandOwner brand owner prompt
     * @param tradeChannels trade channels prompt
     * @param startDate start date prompt
     * @param endDate end date prompt
     */
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

    /**
     * Public constructor (everything else default)
     * @param query search query
     * @param dataType data types to search
     */
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

    /**
     * Setter for the data types
     * @param dataType data types
     */
    public void setDataType(String[] dataType) {
        this.dataType = dataType;
    }

    /**
     * Setter for the page size
     * @param pageSize page size
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Setter for the page number
     * @param pageNumber page number
     */
    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Setter for the sort by prompt
     * @param sortBy sort by prompt
     */
    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    /**
     * Setter for the sort order prompt
     * @param sortOrder sort order prompt
     */
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * Setter for the brand owner prompt
     * @param brandOwner brand owner prompt
     */
    public void setBrandOwner(String brandOwner) {
        this.brandOwner = brandOwner;
    }

    /**
     * Setter for the trade channel prompt
     * @param tradeChannel trade channel prompt
     */
    public void setTradeChannel(String[] tradeChannel) {
        this.tradeChannel = tradeChannel;
    }

    /**
     * Setter for the start date prompt
     * @param startDate start date prompt
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * Setter for the end date prompt
     * @param endDate end date prompt
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Getter for the data types
     * @return data types
     */
    public String[] getDataType() {
        return dataType;
    }

    /**
     * Getter for the page size
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
     * Getter for the brand owner prompt
     * @return brand owner prompt
     */
    public String getBrandOwner() {
        return brandOwner;
    }

    /**
     * Getter for the trade channel prompt
     * @return trade channel prompt
     */
    public String[] getTradeChannel() {
        return tradeChannel;
    }

    /**
     * Getter for the start date prompt
     * @return start date prompt
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Getter for the end date prompt
     * @return end date prompt
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Getter for the request URL
     * @return request URL
     */
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
