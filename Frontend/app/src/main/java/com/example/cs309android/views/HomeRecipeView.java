package com.example.cs309android.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cs309android.R;
import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.request.recipes.GetRecipeImageRequest;

/**
 * Custom view inflates the home_item_model layout.
 * For the recipe list in the homepage
 *
 * @author Mitch Hudson
 * @author Travis Massner
 */
public class HomeRecipeView extends FrameLayout {
    public HomeRecipeView(@NonNull Context context) {
        super(context);
    }

    public HomeRecipeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeRecipeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public HomeRecipeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Inflates the home_item_model under this view
     *
     * @param recipe   Used to fill in details for the view
     * @param listener Runs when the item is clicked
     */
    public void initView(Recipe recipe, OnClickListener listener) {
        inflate(getContext(), R.layout.home_item_model, this);

        new GetRecipeImageRequest(String.valueOf(recipe.getRecipeID()))
                .request((ImageView) findViewById(R.id.recipeImage), getContext());

        ((TextView) findViewById(R.id.recipeTitle)).setText(recipe.getRname());
        ((TextView) findViewById(R.id.recipeDescription)).setText(recipe.getDescription());

        setOnClickListener(listener);
    }

    public void setImage(Bitmap image) {
        ((ImageView) findViewById(R.id.recipeImage)).setImageBitmap(image);
    }
}
