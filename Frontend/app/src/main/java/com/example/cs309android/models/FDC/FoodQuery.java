package com.example.cs309android.models.FDC;

import static com.example.cs309android.models.FDC.Constants.API_KEY_GET;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.cs309android.models.FDC.models.AbridgedFoodItem;
import com.example.cs309android.models.FDC.models.BrandedFoodItem;
import com.example.cs309android.models.FDC.models.FoundationFoodItem;
import com.example.cs309android.models.FDC.models.SRLegacyFoodItem;
import com.example.cs309android.models.FDC.models.SurveyFoodItem;
import com.example.cs309android.models.FDC.queries.FoodsCriteria;
import com.example.cs309android.util.RequestHandler;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class FoodQuery {
    public static void query(FoodsCriteria criteria, int index, Response.Listener<JSONObject> listener, Context context) {
        String url = Constants.API_URL_FOOD_ENDPOINT + criteria.setIndex(index).toString() + API_KEY_GET;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, listener, Throwable::printStackTrace);
        RequestHandler.getInstance(context).add(request);
    }

    public static class Result {
        private final ArrayList<AbridgedFoodItem> abridgedFoodItems;
        private final ArrayList<BrandedFoodItem> brandedFoodItems;
        private final ArrayList<FoundationFoodItem> foundationFoodItems;
        private final ArrayList<SRLegacyFoodItem> srLegacyFoodItems;
        private final ArrayList<SurveyFoodItem> surveyFoodItems;

        public Result(ArrayList<AbridgedFoodItem> abridgedFoodItems, ArrayList<BrandedFoodItem> brandedFoodItems, ArrayList<FoundationFoodItem> foundationFoodItems, ArrayList<SRLegacyFoodItem> srLegacyFoodItems, ArrayList<SurveyFoodItem> surveyFoodItems) {
            this.abridgedFoodItems = abridgedFoodItems;
            this.brandedFoodItems = brandedFoodItems;
            this.foundationFoodItems = foundationFoodItems;
            this.srLegacyFoodItems = srLegacyFoodItems;
            this.surveyFoodItems = surveyFoodItems;
        }

        public ArrayList<AbridgedFoodItem> getAbridgedFoodItems() {
            return abridgedFoodItems;
        }

        public ArrayList<BrandedFoodItem> getBrandedFoodItems() {
            return brandedFoodItems;
        }

        public ArrayList<FoundationFoodItem> getFoundationFoodItems() {
            return foundationFoodItems;
        }

        public ArrayList<SRLegacyFoodItem> getSrLegacyFoodItems() {
            return srLegacyFoodItems;
        }

        public ArrayList<SurveyFoodItem> getSurveyFoodItems() {
            return surveyFoodItems;
        }
    }

    public static class ResultDeserializer implements JsonDeserializer<Result> {
        @Override
        public Result deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            ArrayList<AbridgedFoodItem> abridgedFoodItems = new ArrayList<>();
            ArrayList<BrandedFoodItem> brandedFoodItems = new ArrayList<>();
            ArrayList<FoundationFoodItem> foundationFoodItems = new ArrayList<>();
            ArrayList<SRLegacyFoodItem> srLegacyFoodItems = new ArrayList<>();
            ArrayList<SurveyFoodItem> surveyFoodItems = new ArrayList<>();

            JsonObject obj = json.getAsJsonObject();
            for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                JsonElement entryElement = entry.getValue();
                String dataType = entryElement.getAsJsonObject().get("dataType").getAsString();
                if (dataType.equals(Constants.DataType.BRANDED.getValue())) {
                    brandedFoodItems.add(context.deserialize(entryElement, BrandedFoodItem.class));
                } else if (dataType.equals(Constants.DataType.FOUNDATION.getValue())) {
                    foundationFoodItems.add(context.deserialize(entryElement, FoundationFoodItem.class));
                } else if (dataType.equals(Constants.DataType.SURVEY.getValue())) {
                    surveyFoodItems.add(context.deserialize(entryElement, SurveyFoodItem.class));
                } else if (dataType.equals(Constants.DataType.SR.getValue())) {
                    srLegacyFoodItems.add(context.deserialize(entryElement, SRLegacyFoodItem.class));
                } else {
                    abridgedFoodItems.add(context.deserialize(entryElement, AbridgedFoodItem.class));
                }
            }

            return new Result(abridgedFoodItems, brandedFoodItems, foundationFoodItems, srLegacyFoodItems, surveyFoodItems);
        }
    }
}
