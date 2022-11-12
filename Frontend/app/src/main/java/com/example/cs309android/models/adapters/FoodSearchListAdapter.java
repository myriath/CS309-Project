package com.example.cs309android.models.adapters;

import static com.example.cs309android.util.Constants.CALLBACK_FOOD_DETAIL;
import static com.example.cs309android.util.Constants.PARCEL_FOODITEM;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cs309android.R;
import com.example.cs309android.activities.SearchActivity;
import com.example.cs309android.models.gson.models.SimpleFoodItem;

import java.util.ArrayList;

/**
 * Custom adapter to display the list of food items in the shopping list fragment.
 *
 * @author Mitch Hudson
 */
public class FoodSearchListAdapter extends ArrayAdapter<SimpleFoodItem> {
    /**
     * List of items in the shopping list
     */
    private final ArrayList<SimpleFoodItem> items;

    /**
     * Public constructor.
     *
     * @param context context used by the superclass {@link ArrayAdapter}
     * @param items   list of items to display.
     */
    public FoodSearchListAdapter(Context context, ArrayList<SimpleFoodItem> items) {
        super(context, R.layout.food_search_branded, items);
        this.items = items;
    }

    /**
     * Ran for each of the child views (items in the list)
     * Here is where button functionality for each item is given.
     *
     * @param position    index of the item in the list
     * @param convertView converted view of the item in the list
     * @param parent      ListView parent
     * @return inflated view of the custom list_item view.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleFoodItem item = items.get(position);
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.food_search_branded, null);
        }

        convertView.setClickable(true);
        convertView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(PARCEL_FOODITEM, item);
            ((SearchActivity) parent.getContext()).callback(CALLBACK_FOOD_DETAIL, bundle);
        });

        TextView name = convertView.findViewById(R.id.name);
        name.setText(item.getDescription());

        TextView brand = convertView.findViewById(R.id.brand);
        if (item.getBrand() != null && !item.getBrand().equals("")) {
            brand.setText(item.getBrand());
        } else {
            brand.setText("");
            brand.setWidth(0);
        }

        int[] attrs = new int[]{R.attr.selectableItemBackground};
        TypedArray array = parent.getContext().obtainStyledAttributes(attrs);
        convertView.setBackgroundResource(array.getResourceId(0, 0));
        array.recycle();

        ViewCompat.setOnApplyWindowInsetsListener(parent, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
            return WindowInsetsCompat.CONSUMED;
        });
        return convertView;
    }
}
