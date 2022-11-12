package com.example.cs309android.fragments.nutrition;

import static com.example.cs309android.util.Constants.PARCEL_FOODITEM;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.SearchActivity;
import com.example.cs309android.activities.food.FoodDetailsActivity;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.interfaces.CallbackFragment;
import com.example.cs309android.models.adapters.NutritionLogAdapter;
import com.example.cs309android.models.api.models.FoodLogItem;
import com.example.cs309android.models.api.models.SimpleFoodItem;
import com.example.cs309android.models.api.request.nutrition.GetDayFoodLogRequest;
import com.example.cs309android.models.api.response.nutrition.GetFoodLogResponse;
import com.example.cs309android.util.Toaster;
import com.example.cs309android.util.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

/**
 * Nutrition page fragment to display nutrition info
 *
 * @author Travis Massner
 */
public class NutritionFragment extends BaseFragment {
    /**
     * Calendar for tracking the date
     */
    Calendar date;
    /**
     * Button to go back in the calendar
     */
    ImageButton leftButton;
    /**
     * Button to go forward in the calendar
     */
    ImageButton rightButton;
    /**
     * Adapter for the list view
     */
    NutritionLogAdapter adapter;
    /**
     * List view to display the nutrition log
     */
    ListView listView;

    /**
     * Used to store the list of simple food items
     */
    private static ArrayList<FoodLogItem> foods;

    /**
     * Main window fragment
     */
    private CallbackFragment nutritionFragment;

    /**
     * Used to launch various activities.
     */
    ActivityResultLauncher<Intent> foodSearchLauncher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        date = Calendar.getInstance();

        //THIS IS JUST TEST DATA
        foods = new ArrayList<>();
        FoodLogItem foodLog = new FoodLogItem("papajohn", "chicken");
        foods.add(foodLog);
        foodLog = new FoodLogItem("papajohn", "turkey");
        foods.add(foodLog);
        foodLog = new FoodLogItem("papajohn", "mayo");
        foods.add(foodLog);
        foodLog = new FoodLogItem("papajohn", "bread");
        foods.add(foodLog);

//        foodSearchLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                result -> {
//                    if (result.getResultCode() == RESULT_OK) {
//                        nutritionFragment = NutritionFragment.newInstance(Objects.requireNonNull(result.getData()).getParcelableArrayListExtra(MainActivity.PARCEL_FOODITEMS_LIST));
//                        System.out.println("Food search result okay");
//                        Toaster.toastShort("Food search result okay", requireContext());
//                    } else {
//                        System.out.println("Food search result not okay");
//                        nutritionFragment = new NutritionFragment();
//                    }
//                    System.out.println(result.getData());
//
//                    nutritionFragment.setCallbackFragment(this);
//
//                }
//        );
    }

    /**
     * Updates the current date of the log
     */
    private void updateDate() {
        TextView dateText = requireActivity().findViewById(R.id.date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        dateText.setText(format.format(date.getTime()));

        String dateStr = format.format(date.getTime());
        new GetDayFoodLogRequest(dateStr, ((GlobalClass) requireActivity().getApplicationContext()).getToken()).request(response -> {
            try {
                System.out.print(response.toString(3));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GetFoodLogResponse recipeResponse = Util.objFromJson(response, GetFoodLogResponse.class);


            FoodLogItem[] newItems = recipeResponse.getFoodLog();
            foods = new ArrayList<>();
            foods.addAll(Arrays.asList(newItems));

        }, requireContext());

    }

    /**
     * Runs when the view is created
     *
     * @param inflater           Inflates the fragment view
     * @param container          Parent for the fragment
     * @param savedInstanceState Saved state
     * @return Inflated fragment view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nutrition, container, false);

        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.nutrition_frame), (v, windowInsets) -> {
            Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            mlp.topMargin = insets.top;
            insets = windowInsets.getInsets(WindowInsetsCompat.Type.ime());
            mlp.bottomMargin = insets.bottom;
            return WindowInsetsCompat.CONSUMED;
        });

        leftButton = view.findViewById(R.id.previous_date_button);
        rightButton = view.findViewById(R.id.next_date_button);
        TextView dateText = view.findViewById(R.id.date);
        FloatingActionButton fab = view.findViewById(R.id.fab);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateStr = format.format(date.getTime());
        new GetDayFoodLogRequest(dateStr, ((GlobalClass) requireActivity().getApplicationContext()).getToken()).request(response -> {
            try {
                System.out.print(response.toString(3));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GetFoodLogResponse recipeResponse = Util.objFromJson(response, GetFoodLogResponse.class);

            FoodLogItem[] newItems = recipeResponse.getFoodLog();
            foods = new ArrayList<>();
            foods.addAll(Arrays.asList(newItems));
            if (recipeResponse == null) {
                Toaster.toastShort("Error getting recipes", requireContext());
            }

        }, requireContext());

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            foodSearchLauncher.launch(intent);
        });

        leftButton.setOnClickListener(v -> {
            date.add(Calendar.DATE, -1);
            updateDate();
            refreshList(view);
        });

        rightButton.setOnClickListener(v -> {
            date.add(Calendar.DATE, 1);
            updateDate();
            refreshList(view);
        });

        dateText.setOnClickListener(v -> {
            date = Calendar.getInstance();
            updateDate();
            refreshList(view);
        });

        refreshList(view);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            SimpleFoodItem selectedItem = (SimpleFoodItem) parent.getItemAtPosition(position);
            Intent i = new Intent(getActivity(), FoodDetailsActivity.class);
            i.putExtra(PARCEL_FOODITEM, selectedItem);
            startActivity(i);
        });

        format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        dateText.setText(format.format(date.getTime()));

        return view;
    }

    /**
     * Refreshes the nutrition log
     *
     * @param view
     */
    public void refreshList(View view) {
        adapter = new NutritionLogAdapter(this.getActivity(), foods);
        listView = view.findViewById(R.id.log_list);
        listView.setAdapter(adapter);
    }
}
