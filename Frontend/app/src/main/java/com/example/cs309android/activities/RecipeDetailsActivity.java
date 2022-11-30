package com.example.cs309android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.example.cs309android.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RecipeDetailsActivity extends AppCompatActivity {
    private int fdcId;

    public static final String PARCEL_BUTTON_CONTROL = "button-control";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        Intent i = getIntent();
        FloatingActionButton fab = findViewById(R.id.add_item);
        if (!i.getBooleanExtra(RecipeDetailsActivity.PARCEL_BUTTON_CONTROL, false)) {
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