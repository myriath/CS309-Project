package com.example.cs309android.models.gson.models;

import com.example.cs309android.models.USDA.Constants;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Simple model used to check data types of food items from the USDA API
 *
 * @author Mitch Hudson
 */
public class DataTypeModel {
    /**
     * Data type of the food item
     */
    private final Constants.DataType dataType;

    /**
     * Public constructor
     *
     * @param dataType DataType of the food item
     */
    public DataTypeModel(Constants.DataType dataType) {
        this.dataType = dataType;
    }

    /**
     * Getter for the data type
     *
     * @return Data type
     */
    public Constants.DataType getDataType() {
        return dataType;
    }

    /**
     * Custom GSON deserializer to deserialize the string into DataType
     */
    public static class DataTypeModelDeserializer implements JsonDeserializer<DataTypeModel> {
        @Override
        public DataTypeModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return new DataTypeModel(Constants.DataType.forCode(json.getAsJsonObject().get("dataType").getAsString()));
        }
    }
}
