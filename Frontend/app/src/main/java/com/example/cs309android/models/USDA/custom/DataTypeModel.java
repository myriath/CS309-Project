package com.example.cs309android.models.USDA.custom;

import com.example.cs309android.models.USDA.Constants;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class DataTypeModel {
    private final Constants.DataType dataType;

    public DataTypeModel(Constants.DataType dataType) {
        this.dataType = dataType;
    }

    public Constants.DataType getDataType() {
        return dataType;
    }

    public static class DataTypeModelDeserializer implements JsonDeserializer<DataTypeModel> {
        @Override
        public DataTypeModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return new DataTypeModel(Constants.DataType.forCode(json.getAsJsonObject().get("dataType").getAsString()));
        }
    }
}
