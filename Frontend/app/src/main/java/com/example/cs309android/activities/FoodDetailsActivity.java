package com.example.cs309android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.android.volley.toolbox.NetworkImageView;
import com.example.cs309android.R;
import com.example.cs309android.models.Nutritionix.instant.FoodItem;
import com.example.cs309android.models.Nutritionix.instant.Photo;
import com.example.cs309android.util.RequestHandler;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FoodDetailsActivity extends AppCompatActivity {
    private FoodItem item;

    public static final String PARCEL_BUTTON_CONTROL = "button-control";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        Intent i = getIntent();
        item = i.getParcelableExtra(MainActivity.PARCEL_FOODITEM);

        FloatingActionButton fab = findViewById(R.id.add_item);
        if (!i.getBooleanExtra(FoodDetailsActivity.PARCEL_BUTTON_CONTROL, false)) {
            fab.setVisibility(View.GONE);
        }

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(item.getFoodName().substring(0, Math.min(item.getFoodName().length(), 18)).trim() + (item.getFoodName().length() > 18 ? "..." : ""));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view1 -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        findViewById(R.id.add_item).setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.putExtra(MainActivity.PARCEL_FOODITEM, item);
            setResult(RESULT_OK, intent);
            finish();
        });

        NetworkImageView imageView = findViewById(R.id.image_view);
        try {
            Photo photo = item.getPhoto();
            System.out.println(photo);
            if (photo.getHighres() != null) {
                imageView.setImageUrl(photo.getHighres(), RequestHandler.getInstance(this).getImageLoader());
            } else {
                imageView.setImageUrl(photo.getThumb(), RequestHandler.getInstance(this).getImageLoader());
            }
        } catch (NullPointerException ignored) {}
    }
}