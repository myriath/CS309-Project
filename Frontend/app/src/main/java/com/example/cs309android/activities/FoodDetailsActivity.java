package com.example.cs309android.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.android.volley.toolbox.NetworkImageView;
import com.example.cs309android.R;
import com.example.cs309android.models.Nutritionix.FoodItem;
import com.example.cs309android.util.RequestHandler;
import com.google.android.material.appbar.MaterialToolbar;

public class FoodDetailsActivity extends AppCompatActivity {
    private FoodItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        item = getIntent().getParcelableExtra(MainActivity.PARCEL_FOODITEM);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(item.getName().substring(0, Math.min(item.getName().length(), 18)).trim() + (item.getName().length() > 18 ? "..." : ""));
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
        imageView.setImageUrl("https://cdn.discordapp.com/attachments/545384256617185293/1020782691714613298/unknown.png", RequestHandler.getInstance(this).getImageLoader());

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.collapsing_toolbar), (v, windowInsets) -> {
//            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
////            v.setPadding(0, insets.top, 0, 0);
//            System.out.println(insets.top);
//            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
//            return WindowInsetsCompat.CONSUMED;
//        });
    }
}