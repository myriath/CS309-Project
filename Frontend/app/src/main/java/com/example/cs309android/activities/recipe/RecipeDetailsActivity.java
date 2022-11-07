package com.example.cs309android.activities.recipe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.WindowCompat;

import com.example.cs309android.R;
import com.example.cs309android.models.gson.models.SimpleRecipeItem;

import java.util.Objects;

public class RecipeDetailsActivity extends AppCompatActivity {
    private int fdcId;

    /**
     * Recipe item to display details for
     */
    private SimpleRecipeItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        Toolbar toolbar = findViewById(R.id.toolbar);
        Intent i = getIntent();
        item = i.getParcelableExtra("HomeFragment.recipe");

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(item.getRecipeName());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);

        ImageView image = findViewById(R.id.image_view);
        TextView recipeInstructions = findViewById(R.id.recipe_instructions);
        recipeInstructions.setText(item.getInstructions());

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

    /**
     * Handles the back button on the toolbar
     *
     * @return true
     */
    @Override
    public boolean onSupportNavigateUp() {
        setResult(RESULT_CANCELED);
        finish();
        return true;
    }
}