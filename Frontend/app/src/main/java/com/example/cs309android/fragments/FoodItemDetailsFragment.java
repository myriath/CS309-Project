package com.example.cs309android.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
import com.example.cs309android.models.FoodItem;

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

//        view.findViewById(R.id.)

        return view;
    }
}