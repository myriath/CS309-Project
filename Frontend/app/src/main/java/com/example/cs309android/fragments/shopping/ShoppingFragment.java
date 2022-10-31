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

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.activities.SearchActivity;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.adapters.ShoppingListAdapter;
import com.example.cs309android.models.gson.models.SimpleFoodItem;
import com.example.cs309android.models.gson.request.shopping.GetListRequest;
import com.example.cs309android.models.gson.response.shopping.GetListResponse;
import com.example.cs309android.util.Constants;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

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
    private static ArrayList<SimpleFoodItem> items;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShoppingFragment.
     */
    public static ShoppingFragment newInstance(ArrayList<SimpleFoodItem> items) {
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

        if (items == null) {
            items = new ArrayList<>();
            new GetListRequest(((GlobalClass) requireActivity().getApplicationContext()).getToken()).request(response -> {
                GetListResponse getResponse = Util.objFromJson(response, GetListResponse.class);
                if (getResponse.getResult() == Constants.RESULT_OK) {
                    items.addAll(Arrays.asList(getResponse.getShoppingList()));

                    refreshList(view);
                } else {
                    Toaster.toastShort("Error", getContext());
                }
            }, requireActivity());
        }

        refreshList(view);

        view.findViewById(R.id.add_item).setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(MainActivity.PARCEL_FOODITEMS_LIST, items);
            bundle.putInt(MainActivity.PARCEL_INTENT_CODE, SearchActivity.INTENT_SHOPPING_LIST);
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

    public void refreshList(View view) {
        ShoppingListAdapter adapter = new ShoppingListAdapter(this.getActivity(), items);
        ((ListView) view.findViewById(R.id.shopping_list)).setAdapter(adapter);
        ExtendedFloatingActionButton fab = view.findViewById(R.id.add_item);

        TextView empty = view.findViewById(R.id.empty_text);
        if (items.isEmpty()) {
            empty.setVisibility(View.VISIBLE);
            fab.extend();
        } else {
            empty.setVisibility(View.INVISIBLE);
            fab.shrink();
        }
    }

    /**
     * Removes the given item from the list
     *
     * @param i index of the item to remove
     * @return True if the items list is empty
     */
    public static boolean removeItem(int i) {
        items.remove(i);
        return items.isEmpty();
    }

    /**
     * Clears the item list.
     */
    public static void clearItems() {
        items = null;
    }
}