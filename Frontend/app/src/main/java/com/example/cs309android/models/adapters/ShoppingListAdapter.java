package com.example.cs309android.models.adapters;

import static com.example.cs309android.util.Constants.ITEM_ID_NULL;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Response;
import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.fragments.shopping.ShoppingFragment;
import com.example.cs309android.models.gson.models.SimpleFoodItem;
import com.example.cs309android.models.gson.request.shopping.ShoppingRemoveRequest;
import com.example.cs309android.models.gson.request.shopping.StrikeCustomRequest;
import com.example.cs309android.models.gson.request.shopping.StrikeRequest;
import com.example.cs309android.models.gson.response.GenericResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Custom adapter to display the list of food items in the shopping list fragment.
 *
 * @author Mitch Hudson
 */
public class ShoppingListAdapter extends ArrayAdapter<SimpleFoodItem> {
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
    public ShoppingListAdapter(Context context, ArrayList<SimpleFoodItem> items) {
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
        }

        final GlobalClass globalVariable = (GlobalClass) getContext().getApplicationContext();

        convertView.setClickable(true);
        convertView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(MainActivity.PARCEL_FOODITEM, items.get(position));
            ((MainActivity) parent.getContext()).callback(MainActivity.CALLBACK_FOOD_DETAIL, bundle);
        });

        TextView name = convertView.findViewById(R.id.name);
        name.setText(items.get(position).getDescription());
        if (items.get(position).isStricken()) {
            name.setPaintFlags(name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            name.setEnabled(false);
            ((CheckBox) convertView.findViewById(R.id.stricken)).setChecked(true);
        } else {
            name.setPaintFlags(name.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            name.setEnabled(true);
            ((CheckBox) convertView.findViewById(R.id.stricken)).setChecked(false);
        }

        convertView.findViewById(R.id.stricken).setOnClickListener(view -> {
            CheckBox checkBox = (CheckBox) view;
            Response.Listener<JSONObject> strikeSet = response -> {
                GenericResponse genericResponse = Util.objFromJson(response, GenericResponse.class);
                if (genericResponse.getResult() == Constants.RESULT_OK) {
                    name.setPaintFlags(name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    name.setEnabled(false);
                } else {
                    Toaster.toastShort("Error", getContext());
                    checkBox.setChecked(false);
                }
            };
            Response.Listener<JSONObject> strikeRemove = response -> {
                GenericResponse genericResponse = Util.objFromJson(response, GenericResponse.class);
                if (genericResponse.getResult() == Constants.RESULT_OK) {
                    name.setPaintFlags(name.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    name.setEnabled(true);
                } else {
                    Toaster.toastShort("Error", getContext());
                    checkBox.setChecked(true);
                }
            };

            SimpleFoodItem item = items.get(position);
            if (checkBox.isChecked()) {
                if (item.getId() == ITEM_ID_NULL) {
                    new StrikeCustomRequest(item.getId(), globalVariable.getToken()).request(strikeSet, getContext());
                } else {
                    new StrikeRequest(item.getId(), globalVariable.getToken()).request(strikeSet, getContext());
                }
            } else {
                if (item.getId() == ITEM_ID_NULL) {
                    new StrikeCustomRequest(item.getId(), globalVariable.getToken()).request(strikeRemove, getContext());
                } else {
                    new StrikeRequest(item.getId(), globalVariable.getToken()).request(strikeRemove, getContext());
                }            }
            items.get(position).setStricken(checkBox.isChecked());
        });

        convertView.findViewById(R.id.remove).setOnClickListener(view1 ->
                new ShoppingRemoveRequest(items.get(position).getId(), globalVariable.getToken()).request(response -> {
                    GenericResponse genericResponse = Util.objFromJson(response, GenericResponse.class);
                    if (genericResponse.getResult() == Constants.RESULT_OK) {
                        if (ShoppingFragment.removeItem(position)) {
                            ((ExtendedFloatingActionButton) view1.getRootView().findViewById(R.id.add_item)).extend();
                            ((TextView) view1.getRootView().findViewById(R.id.empty_text)).setVisibility(View.VISIBLE);
                        } else {
                            ((ExtendedFloatingActionButton) view1.getRootView().findViewById(R.id.add_item)).shrink();
                        }
                        ((ListView) view1.getRootView().findViewById(R.id.shopping_list)).setAdapter(this);
                    } else {
                        Toaster.toastShort("Error", getContext());
                    }
                }, getContext())
        );

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
