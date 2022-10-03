package com.example.cs309android.activities;

import static com.example.cs309android.models.USDA.Constants.Format;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.example.cs309android.R;
import com.example.cs309android.models.USDA.models.AbridgedFoodItem;
import com.example.cs309android.models.USDA.models.BrandedFoodItem;
import com.example.cs309android.models.USDA.models.FoundationFoodItem;
import com.example.cs309android.models.USDA.models.SRLegacyFoodItem;
import com.example.cs309android.models.USDA.models.SurveyFoodItem;
import com.example.cs309android.models.USDA.queries.FoodsCriteria;
import com.example.cs309android.models.gson.models.DataTypeModel;
import com.example.cs309android.models.gson.models.SimpleFoodItem;
import com.example.cs309android.util.Util;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FoodDetailsActivity extends AppCompatActivity {
    public static final String PARCEL_BUTTON_CONTROL = "button-control";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(view1 -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        Intent i = getIntent();
        int fdcId = ((SimpleFoodItem) i.getParcelableExtra(MainActivity.PARCEL_FOODITEM)).getFdcId();
        new FoodsCriteria(fdcId, Format.FULL, null).request(response -> {
            DataTypeModel dataType = Util.objFromJsonAdapted(response.toString(), DataTypeModel.class, new DataTypeModel.DataTypeModelDeserializer());
            switch (dataType.getDataType()) {
                case BRANDED: {
                    BrandedFoodItem foodItem = Util.objFromJson(response, BrandedFoodItem.class);
                    setTitle(foodItem.getDescription(), toolbar);
                    break;
                }
                case FOUNDATION: {
                    FoundationFoodItem foodItem = Util.objFromJson(response, FoundationFoodItem.class);
                    setTitle(foodItem.getDescription(), toolbar);
                    break;
                }
                case SURVEY: {
                    SurveyFoodItem foodItem = Util.objFromJson(response, SurveyFoodItem.class);
//                    setTitle(foodItem.getDescription(), toolbar);
                    break;
                }
                case LEGACY: {
                    SRLegacyFoodItem foodItem = Util.objFromJson(response, SRLegacyFoodItem.class);
                    setTitle(foodItem.getDescription(), toolbar);
                    break;
                }
                default: {
                    AbridgedFoodItem foodItem = Util.objFromJson(response, AbridgedFoodItem.class);
                    setTitle(foodItem.getDescription(), toolbar);
                }
            }
        }, this);

        FloatingActionButton fab = findViewById(R.id.add_item);
        if (!i.getBooleanExtra(FoodDetailsActivity.PARCEL_BUTTON_CONTROL, false)) {
            fab.setVisibility(View.GONE);
        }
//z
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

    private void setTitle(String title, MaterialToolbar toolbar) {
        toolbar.setTitle(title.substring(0, Math.min(title.length(), 18)).trim() + (title.length() > 18 ? "..." : ""));
        setSupportActionBar(toolbar);
    }
}