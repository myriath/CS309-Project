package com.example.cs309android.models.USDA;

/**
 * Constants util class for USDA objects
 *
 * @author Mitch Hudson
 */
public class Constants {
    public static final DataType[] SEARCH_ALL_TYPES = new DataType[]{DataType.BRANDED, DataType.SURVEY, DataType.LEGACY, DataType.FOUNDATION};
    public static final String API_KEY = "ku3IulAMpmefeIA26Op2ZSN80uObLD4bwwjAoXns";
    public static final String API_KEY_GET = "&api_key=" + API_KEY;
    public static final String API_URL = "https://api.nal.usda.gov/fdc";
    public static final String API_URL_FOOD_ENDPOINT = API_URL + "/v1/food/";
    public static final String API_URL_FOODS_ENDPOINT = API_URL + "/v1/foods";
    public static final String API_URL_LIST_ENDPOINT = API_URL + "/v1/foods/list";
    public static final String API_URL_SEARCH_ENDPOINT = API_URL + "/v1/foods/search";
    public static final String API_URL_JSON_SPEC_ENDPOINT = API_URL + "/v1/json-spec";
    public static final String API_URL_YAML_SPEC_ENDPOINT = API_URL + "/v1/yaml-spec";

    /**
     * Util class
     */
    private Constants() {
    }

    /**
     * Possible DataType values for the dataType parameter
     */
    public enum DataType {
        BRANDED("Branded"),
        FOUNDATION("Foundation"),
        SURVEY("Survey (FNDDS)"),
        LEGACY("SR Legacy"),
        ABRIDGED("else");

        private final String value;

        DataType(String value) {
            this.value = value;
        }

        public static DataType forCode(String value) {
            for (DataType dataType : values()) {
                if (dataType.getValue().equals(value)) return dataType;
            }
            return ABRIDGED;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * Possible SortBy values for the sortBy parameter
     */
    public enum SortBy {
        DATA_TYPE("dataType.keyword"),
        DESCRIPTION("lowercaseDescription.keyword"),
        FDC_ID("fdcId"),
        PUBLISHED_DATE("publishedDate");

        private final String value;

        SortBy(String value) {
            this.value = value;
        }

        public static SortBy forCode(String value) {
            for (SortBy sortBy : values()) {
                if (sortBy.getValue().equals(value)) return sortBy;
            }
            return null;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * Possible SortOrder values for the sortOrder parameter
     */
    public enum SortOrder {
        ASCENDING("asc"),
        DESCENDING("desc");

        private final String value;

        SortOrder(String value) {
            this.value = value;
        }

        public static SortOrder forCode(String value) {
            for (SortOrder sortOrder : values()) {
                if (sortOrder.getValue().equals(value)) return sortOrder;
            }
            return null;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * Possible TradeChannel values for the tradeChannel parameter
     */
    public enum TradeChannel {
        CHILD_NUTRITION_FOOD_PROGRAMS("CHILD_NUTRITION_FOOD_PROGRAMS"),
        DRUG("DRUG"),
        FOOD_SERVICE("FOOD_SERVICE"),
        GROCERY("GROCERY"),
        MASS_MERCHANDISING("MASS_MERCHANDISING"),
        MILITARY("MILITARY"),
        ONLINE("ONLINE"),
        VENDING("VENDING");

        private final String value;

        TradeChannel(String value) {
            this.value = value;
        }

        public static TradeChannel forCode(String value) {
            for (TradeChannel tradeChannel : values()) {
                if (tradeChannel.getValue().equals(value)) return tradeChannel;
            }
            return null;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * Possible Format values for the format parameter
     */
    public enum Format {
        ABRIDGED("abridged"),
        FULL("full");

        private final String value;

        Format(String value) {
            this.value = value;
        }

        public static Format forCode(String value) {
            for (Format format : values()) {
                if (format.getValue().equals(value)) return format;
            }
            return null;
        }

        public String getValue() {
            return value;
        }
    }
}
