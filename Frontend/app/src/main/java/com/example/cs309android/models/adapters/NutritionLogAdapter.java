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
import com.example.cs309android.models.gson.models.FoodLogItem;
import com.example.cs309android.models.gson.models.SimpleFoodItem;

import java.util.ArrayList;

public class NutritionLogAdapter extends ArrayAdapter<FoodLogItem> {

    /**
     * List of items in the food log
     */
    public final ArrayList<FoodLogItem> items;


    /**
     * Public constructor.
     *
     * @param context context used by the superclass {@link ArrayAdapter}
     * @param items   list of items to display.
     */
    public NutritionLogAdapter(Context context, ArrayList<FoodLogItem> items) {
        super(context, R.layout.food_log_item, items);
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
            convertView = View.inflate(getContext(), R.layout.food_log_item, null);
        }

        TextView name = convertView.findViewById(R.id.log_item);
        name.setText(items.get(position).getFoodName());

        ViewCompat.setOnApplyWindowInsetsListener(parent, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
            return WindowInsetsCompat.CONSUMED;
        });

        return convertView;
    }

}
