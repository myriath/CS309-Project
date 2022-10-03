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
    public static final int CONTROL_NONE = 0;
    public static final int CONTROL_ADD = 1;
    public static final int CONTROL_FAV = 2;

    private SimpleFoodItem item;

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
                // TODO: Write all of these, currently only need Branded/Foundation because of search
                case BRANDED: {
                    BrandedFoodItem foodItem = Util.objFromJson(response, BrandedFoodItem.class);
                    item = new SimpleFoodItem(foodItem.getFdcId(), foodItem.getDescription());
                    setTitle(foodItem.getDescription(), toolbar);
                    break;
                }
                case FOUNDATION: {
                    FoundationFoodItem foodItem = Util.objFromJson(response, FoundationFoodItem.class);
                    item = new SimpleFoodItem(foodItem.getFdcId(), foodItem.getDescription());
                    setTitle(foodItem.getDescription(), toolbar);
                    break;
                }
                case SURVEY: {
                    SurveyFoodItem foodItem = Util.objFromJson(response, SurveyFoodItem.class);
//                    item = new SimpleFoodItem(foodItem.getFdcId(), foodItem.getDescription());
//                    setTitle(foodItem.getDescription(), toolbar);
                    break;
                }
                case LEGACY: {
                    SRLegacyFoodItem foodItem = Util.objFromJson(response, SRLegacyFoodItem.class);
                    item = new SimpleFoodItem(foodItem.getFdcId(), foodItem.getDescription());
                    setTitle(foodItem.getDescription(), toolbar);
                    break;
                }
                default: {
                    AbridgedFoodItem foodItem = Util.objFromJson(response, AbridgedFoodItem.class);
                    item = new SimpleFoodItem(foodItem.getFdcId(), foodItem.getDescription());
                    setTitle(foodItem.getDescription(), toolbar);
                }
            }
        }, this);

        FloatingActionButton fab = findViewById(R.id.add_item);
        int control = i.getIntExtra(FoodDetailsActivity.PARCEL_BUTTON_CONTROL, CONTROL_NONE);
        if (control == CONTROL_ADD) fab.setVisibility(View.VISIBLE);

        fab.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra(MainActivity.PARCEL_FOODITEM, item);
            setResult(RESULT_OK, intent);
            finish();
        });
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