package com.example.cs309android.models.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cs309android.R;
import com.example.cs309android.models.gson.models.SimpleRecipeItem;

import java.util.ArrayList;

public class HomeItemAdapter extends ArrayAdapter<SimpleRecipeItem> {
    /**
     * List of items in the recipe list
     */
    private final ArrayList<SimpleRecipeItem> items;

    /**
     * Public constructor.
     *
     * @param context context used by the superclass {@link ArrayAdapter}
     * @param items   list of items to display.
     */
    public HomeItemAdapter(Context context, ArrayList<SimpleRecipeItem> items) {
        super(context, R.layout.home_item_modal, items);
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
        if (convertView == null) {
            convertView = View.inflate(getContext(), R.layout.home_item_modal, null);
        }

        TextView name = convertView.findViewById(R.id.recipe_instructions);
        name.setText(items.get(position).getRecipeName());

        ViewCompat.setOnApplyWindowInsetsListener(parent, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
            return WindowInsetsCompat.CONSUMED;
        });

        return convertView;
    }
}
