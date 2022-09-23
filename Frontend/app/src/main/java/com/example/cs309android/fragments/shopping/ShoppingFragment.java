package com.example.cs309android.fragments.shopping;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.Nutritionix.instant.FoodItem;
import com.example.cs309android.models.adapters.ShoppingListAdapter;

import java.util.ArrayList;

/**
 * Shopping list fragment.
 * Consists of a ListView and a FAB to add items.
 * The list view has a custom adapter that has a checkbox to strike out the text, a label, and
 * a button to remove the item.
 *
 * @author Mitch Hudson
 */
public class ShoppingFragment extends BaseFragment {
    /**
     * All of the items to display
     */
    private static ArrayList<FoodItem> items;
    /**
     * Adapter to display the ListView
     */
    private static ShoppingListAdapter adapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShoppingFragment.
     */
    public static ShoppingFragment newInstance(ArrayList<FoodItem> items) {
        ShoppingFragment fragment = new ShoppingFragment();
        ShoppingFragment.items = items;
        return fragment;
    }

    /**
     * Ran when the fragment is created.
     *
     * @param inflater           inflater
     * @param container          container
     * @param savedInstanceState saved instance state
     * @return inflated view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);

        ListView listView = view.findViewById(R.id.shopping_list);
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

        // TODO: Get from database
        if (items == null) {
            items = new ArrayList<>();
        }

        TextView empty = view.findViewById(R.id.empty_text);
        if (items.isEmpty()) {
            empty.setVisibility(View.VISIBLE);
        } else {
            empty.setVisibility(View.INVISIBLE);
        }

        adapter = new ShoppingListAdapter(this.getActivity(), items);
        listView.setAdapter(adapter);

        view.findViewById(R.id.add_item).setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(MainActivity.PARCEL_FOODITEMS_LIST, items);
            callbackFragment.callback(MainActivity.CALLBACK_SEARCH_FOOD, bundle);
        });

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.shopping_list), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            mlp.topMargin = insets.top;
            insets = windowInsets.getInsets(WindowInsetsCompat.Type.ime());
            mlp.bottomMargin = insets.bottom;
            return WindowInsetsCompat.CONSUMED;
        });

        return view;
    }

    /**
     * Removes the given item from the list
     *
     * @param i    index of the item to remove
     * @param view root view to find the listview/textview
     */
    public static void removeItem(int i, View view) {
        items.remove(i);
        ((ListView) view.findViewById(R.id.shopping_list)).setAdapter(adapter);
        if (items.isEmpty()) {
            ((TextView) view.findViewById(R.id.empty_text)).setVisibility(View.VISIBLE);
        }
    }
}