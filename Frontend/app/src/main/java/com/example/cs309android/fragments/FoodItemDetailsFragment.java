package com.example.cs309android.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cs309android.R;
import com.example.cs309android.activities.FoodSearchActivity;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.models.Nutritionix.FoodItem;
import com.google.android.material.appbar.MaterialToolbar;

/**
 * Displays a food item's details in a readable manner to the user
 *
 * @author Mitch Hudson
 */
public class FoodItemDetailsFragment extends BaseFragment {
    /**
     * FoodItem to display information for
     */
    private FoodItem item;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param item {@link FoodItem} to display information for
     * @return A new instance of fragment FoodItemDetailsFragment.
     */
    public static FoodItemDetailsFragment newInstance(FoodItem item) {
        FoodItemDetailsFragment fragment = new FoodItemDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(MainActivity.PARCEL_FOODITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Runs when the fragment is created.
     * Sets the food item from the arguments.
     *
     * @param savedInstanceState saved instance state
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            item = getArguments().getParcelable(MainActivity.PARCEL_FOODITEM);
        }
    }

    /**
     * Runs when the fragment's view is created
     *
     * @param inflater           inflater
     * @param container          container
     * @param savedInstanceState saved instance state
     * @return inflated view.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_item_details, container, false);

        MaterialToolbar toolbar = view.findViewById(R.id.toolbar);
        System.out.println(item.getName());
        toolbar.setNavigationOnClickListener(view1 -> {
            callbackFragment.callback(FoodSearchActivity.CALLBACK_CLOSE_DETAIL, null);
        });

        ViewCompat.setOnApplyWindowInsetsListener(toolbar, (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, insets.top, 0, 0);
            System.out.println("padding");
//            ((ViewGroup.MarginLayoutParams) v.getLayoutParams()).topMargin = insets.top;
            return WindowInsetsCompat.CONSUMED;
        });

//        requireActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        view.findViewById(R.id.add_item).setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putParcelable(MainActivity.PARCEL_FOODITEM, item);
            callbackFragment.callback(FoodSearchActivity.CALLBACK_SELECT, bundle);
        });

        return view;
    }
}