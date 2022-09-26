package com.example.cs309android.models.FDC.queries;

import androidx.annotation.NonNull;

import com.example.cs309android.models.FDC.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * JSON for request body of 'foods' POST request. Retrieves a list of food items by a list of up to
 * 20 FDC IDs. Optional format and nutrients can be specified. Invalid FDC ID's or ones that are not
 * found are omitted and an empty set is returned if there are no matches.
 * <p>
 * From https://app.swaggerhub.com/apis/fdcnal/food-data_central_api/1.0.1#/FoodsCriteria
 */
public class FoodsCriteria {
    private final Integer[] fdcIds;
    private final String format;
    private final Integer[] nutrients;
    private int index = 0;

    public FoodsCriteria(Integer[] fdcIds, String format, Integer[] nutrients) {
        this.fdcIds = fdcIds;
        this.format = format;
        this.nutrients = nutrients;
    }

    public FoodsCriteria(Integer[] fdcIds, Constants.Format format, Integer[] nutrients) {
        this(fdcIds, format.getValue(), nutrients);
    }

    public FoodsCriteria(int fdcId, Constants.Format format, Integer[] nutrients) {
        this(new Integer[] {fdcId}, format.getValue(), nutrients);
    }

    public Integer[] getFdcIds() {
        return fdcIds;
    }

    public String getFormat() {
        return format;
    }

    public Integer[] getNutrients() {
        return nutrients;
    }

    public FoodsCriteria setIndex(int index) {
        this.index = index;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        ArrayList<String> args = new ArrayList<>();
        try {
            if (fdcIds != null) {
                if (index == -1) {
                    for (Integer id : fdcIds)
                        if (id != null)
                            args.add(String.format("fdcId=%s", URLEncoder.encode(String.valueOf(id), "utf-8")));
                } else {
                    args.add(String.format("%s?", URLEncoder.encode(String.valueOf(fdcIds[index]), "utf-8")));
                }
            }
            if (format != null)
                args.add(String.format("format=%s", URLEncoder.encode(format, "utf-8")));
            if (nutrients != null)
                for (Integer nutrient : nutrients)
                    if (nutrient != null)
                        args.add(String.format("nutrients=%s", URLEncoder.encode(String.valueOf(nutrient), "utf-8")));
            return String.join("&", args);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
