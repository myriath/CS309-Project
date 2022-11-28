package com.example.cs309android.models.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cs309android.R;
import com.example.cs309android.models.api.models.Recipe;
import com.example.cs309android.models.api.request.recipes.GetRecipeImageRequest;

import java.util.ArrayList;

/**
 * Adapter for the list of recipes in various pages
 *
 * @author Travis Massner
 */
public class RecipeListAdapter extends ArrayAdapter<Recipe> {
    /**
     * List of items in the recipe list
     */
    private final ArrayList<Recipe> items;

    /**
     * Public constructor.
     *
     * @param context context used by the superclass {@link ArrayAdapter}
     * @param items   list of items to display.
     */
    public RecipeListAdapter(Context context, ArrayList<Recipe> items) {
        super(context, R.layout.home_item_model, items);
        this.items = items;
    }

    /**
     * Ran for each of the child views (items in the list)
     *
     * @param position    index of the item in the list
     * @param convertView converted view of the item in the list
     * @param parent      ListView parent
     * @return inflated view of the custom list_item view.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Recipe recipe = items.get(position);
        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.home_item_model, null);
        }

        new GetRecipeImageRequest(String.valueOf(recipe.getRecipeID()))
                .request((ImageView) convertView.findViewById(R.id.recipeImage), getContext());

        ((TextView) convertView.findViewById(R.id.recipeTitle)).setText(recipe.getRecipeName());
        ((TextView) convertView.findViewById(R.id.recipeDescription)).setText(recipe.getDescription());

        ViewCompat.setOnApplyWindowInsetsListener(parent, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
            return WindowInsetsCompat.CONSUMED;
        });

        return convertView;
    }
}
