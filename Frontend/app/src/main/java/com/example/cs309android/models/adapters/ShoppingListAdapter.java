package com.example.cs309android.models.adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.fragments.shopping.ShoppingFragment;
import com.example.cs309android.models.Nutritionix.FoodItem;

import java.util.ArrayList;

/**
 * Custom adapter to display the list of food items in the shopping list fragment.
 *
 * @author Mitch Hudson
 */
public class ShoppingListAdapter extends ArrayAdapter<FoodItem> {
    /**
     * List of items in the shopping list
     */
    private final ArrayList<FoodItem> items;

    /**
     * Public constructor.
     *
     * @param context context used by the superclass {@link ArrayAdapter}
     * @param items   list of items to display.
     */
    public ShoppingListAdapter(Context context, ArrayList<FoodItem> items) {
        super(context, R.layout.shopping_list_item, items);
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
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.shopping_list_item, null);

            convertView.setClickable(true);
            convertView.setOnClickListener(view -> {
                Bundle bundle = new Bundle();
                bundle.putParcelable(MainActivity.PARCEL_FOODITEM, items.get(position));
                ((MainActivity) parent.getContext()).callback(MainActivity.CALLBACK_FOOD_DETAIL, bundle);
            });

            TextView name = convertView.findViewById(R.id.name);
            name.setText(items.get(position).getFoodName());

            convertView.findViewById(R.id.stricken).setOnClickListener(view -> {
                if (((CheckBox) view).isChecked()) {
                    name.setPaintFlags(name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    name.setEnabled(false);
                } else {
                    name.setPaintFlags(name.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    name.setEnabled(true);
                }
            });

            convertView.findViewById(R.id.remove).setOnClickListener(view1 -> ShoppingFragment.removeItem(position, parent.getRootView()));

            int[] attrs = new int[]{R.attr.selectableItemBackground};
            TypedArray array = parent.getContext().obtainStyledAttributes(attrs);
            convertView.setBackgroundResource(array.getResourceId(0, 0));
            array.recycle();
        }

        ViewCompat.setOnApplyWindowInsetsListener(parent, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
            return WindowInsetsCompat.CONSUMED;
        });
        return convertView;
    }
}
