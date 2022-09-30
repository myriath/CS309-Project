package com.example.cs309android.activities;

import static com.example.cs309android.models.USDA.Constants.Format;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.example.cs309android.R;
import com.example.cs309android.models.USDA.Queries;
import com.example.cs309android.models.USDA.custom.DataTypeModel;
import com.example.cs309android.models.USDA.custom.SimpleFoodItem;
import com.example.cs309android.models.USDA.models.AbridgedFoodItem;
import com.example.cs309android.models.USDA.models.BrandedFoodItem;
import com.example.cs309android.models.USDA.models.FoundationFoodItem;
import com.example.cs309android.models.USDA.models.SRLegacyFoodItem;
import com.example.cs309android.models.USDA.models.SurveyFoodItem;
import com.example.cs309android.models.USDA.queries.FoodsCriteria;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FoodDetailsActivity extends AppCompatActivity {
    public static final String PARCEL_BUTTON_CONTROL = "button-control";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        Intent i = getIntent();
        int fdcId = ((SimpleFoodItem) i.getParcelableExtra(MainActivity.PARCEL_FOODITEM)).getFdcId();
        Queries.food(new FoodsCriteria(null, Format.FULL, null), fdcId,
                response -> {
                    System.out.println(response.toString());
                    GsonBuilder builder = new GsonBuilder();
                    builder.serializeNulls();
                    builder.registerTypeAdapter(DataTypeModel.class, new DataTypeModel.DataTypeModelDeserializer());
                    Gson gson = builder.create();
                    DataTypeModel dataType = gson.fromJson(response.toString(), DataTypeModel.class);
                    Object foodItem;
                    switch (dataType.getDataType()) {
                        case BRANDED: {
                            foodItem = gson.fromJson(response.toString(), BrandedFoodItem.class);
                            break;
                        }
                        case FOUNDATION: {
                            foodItem = gson.fromJson(response.toString(), FoundationFoodItem.class);
                            break;
                        }
                        case SURVEY: {
                            foodItem = gson.fromJson(response.toString(), SurveyFoodItem.class);
                            break;
                        }
                        case LEGACY: {
                            foodItem = gson.fromJson(response.toString(), SRLegacyFoodItem.class);
                            break;
                        }
                        default: {
                            foodItem = gson.fromJson(response.toString(), AbridgedFoodItem.class);
                        }
                    }

                    System.out.println(foodItem);
                }, this.getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.add_item);
        if (!i.getBooleanExtra(FoodDetailsActivity.PARCEL_BUTTON_CONTROL, false)) {
            fab.setVisibility(View.GONE);
        }

//        MaterialToolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle(item.getFoodName().substring(0, Math.min(item.getFoodName().length(), 18)).trim() + (item.getFoodName().length() > 18 ? "..." : ""));
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(view1 -> {
//            setResult(RESULT_CANCELED);
//            finish();
//        });
//
//        findViewById(R.id.add_item).setOnClickListener(view1 -> {
//            Intent intent = new Intent();
//            intent.putExtra(MainActivity.PARCEL_FOODITEM, item);
//            setResult(RESULT_OK, intent);
//            finish();
//        });
//
//        NetworkImageView imageView = findViewById(R.id.image_view);
//        try {
//            Photo photo = item.getPhoto();
//            System.out.println(photo);
//            if (photo.getHighres() != null) {
//                imageView.setImageUrl(photo.getHighres(), RequestHandler.getInstance(this).getImageLoader());
//            } else {
//                imageView.setImageUrl(photo.getThumb(), RequestHandler.getInstance(this).getImageLoader());
//            }
//        } catch (NullPointerException ignored) {}
    }
}