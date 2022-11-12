package com.example.cs309android.models.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cs309android.R;
import com.example.cs309android.models.api.models.Recipe;

import java.util.ArrayList;

/**
 * Adapter to show a user's feed of recipes
 *
 * @author Travis Massner
 */
public class FeedAdapter extends ArrayAdapter<Recipe> {
    /**
     * List of items in the shopping list
     */
    private final ArrayList<Recipe> items;

    /**
     * Public constructor.
     * @param context context used by the superclass {@link ArrayAdapter}
     * @param items   list of items to display.
     */
    public FeedAdapter(Context context, ArrayList<Recipe> items) {
        super(context, R.layout.shopping_list_item, items);
        this.items = items;
    }

    /**
     * Ran for each of the child views (items in the list)
     * Here is where button functionality for each item is given.
     * @param position    index of the item in the list
     * @param convertView converted view of the item in the list
     * @param parent      ListView parent
     * @return inflated view of the custom list_item view.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Recipe item = items.get(position);
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.food_search_branded, null);
        }

        // TODO: Build item view

        ViewCompat.setOnApplyWindowInsetsListener(parent, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
            return WindowInsetsCompat.CONSUMED;
        });
        return convertView;
    }
}
