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
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.FoodItem;

import java.util.ArrayList;

public class ShoppingListAdapter extends ArrayAdapter<FoodItem> {
    private final ArrayList<FoodItem> items;
    private final CallbackFragment callbackFragment;

    public ShoppingListAdapter(Context context, ArrayList<FoodItem> items, CallbackFragment callbackFragment) {
        super(context, R.layout.shopping_list_item, items);
        this.items = items;
        this.callbackFragment = callbackFragment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.shopping_list_item, null);

            convertView.setClickable(true);

            TextView name = convertView.findViewById(R.id.name);
            name.setText(items.get(position).getName());

            convertView.findViewById(R.id.stricken).setOnClickListener(view -> {
                if (((CheckBox) view).isChecked()) {
                    name.setPaintFlags(name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    name.setEnabled(false);
                } else {
                    name.setPaintFlags(name.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    name.setEnabled(true);
                }
            });

            convertView.findViewById(R.id.remove).setOnClickListener(view1 -> ShoppingFragment.removeItem(position, parent));

            convertView.setOnClickListener(view -> {
                FoodItem item = items.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("fooditem", item.getId());
                callbackFragment.callback(MainActivity.CALLBACK_MOVE_TO_FOOD_ITEM, bundle);
            });

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
