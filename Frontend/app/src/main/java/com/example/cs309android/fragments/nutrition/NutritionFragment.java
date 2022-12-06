package com.example.cs309android.fragments.nutrition;

import static com.example.cs309android.util.Constants.BREAKFAST_LOG;
import static com.example.cs309android.util.Constants.DINNER_LOG;
import static com.example.cs309android.util.Constants.LUNCH_LOG;
import static com.example.cs309android.util.Constants.Parcels.PARCEL_FOODITEM;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cs309android.GlobalClass;
import com.example.cs309android.R;
import com.example.cs309android.activities.MainActivity;
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
import com.google.android.material.divider.MaterialDivider;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

/**
 * Nutrition page fragment to display nutrition info
 * TODO: Calculate or retrieve nutrition data and update the progress bars
 * TODO: Maybe replace FoodLogItem with just a regular simple food item? or otherwise have that information available
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
     * Main window fragment
     */
    private CallbackFragment nutritionFragment;

    /**
     * Stores the value of dp8 in pixels
     */
    private int dp8;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        date = Calendar.getInstance();
        dp8 = (int) Util.scalePixels(8);

    }

    /**
     * Updates the current date of the log
     */
    private void updateDate() {
        TextView dateText = requireActivity().findViewById(R.id.date);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateStr = format.format(date.getTime());
        // TODO: Getter should get a list of each meal, or this needs to split it up
        new GetDayFoodLogRequest(dateStr, ((GlobalClass) requireActivity().getApplicationContext()).getToken()).request(response -> {
            try {
                System.out.println(response.toString(3));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GetFoodLogResponse recipeResponse = Util.objFromJson(response, GetFoodLogResponse.class);
            if (recipeResponse == null) {
                Toaster.toastShort("Error getting recipes", requireContext());
                return;
            }
            SimpleFoodItem[] newItems = recipeResponse.getFoodLog();
            MainActivity.clearFoodLog();
            for (SimpleFoodItem item : newItems) {
                switch (item.getMeal()) {
                    case "Breakfast":
                        MainActivity.addLogItem(item, BREAKFAST_LOG);
                        break;
                    case "Lunch":
                        MainActivity.addLogItem(item, LUNCH_LOG);
                        break;
                    case "Dinner":
                        MainActivity.addLogItem(item, DINNER_LOG);
                        break;
                }
            }
        }, requireContext());
        refreshList(requireView());

        format = new SimpleDateFormat("EEE MMM, d, yyyy", Locale.getDefault());
        dateText.setText(format.format(date.getTime()));
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

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateStr = format.format(date.getTime());
        // TODO: Getter should get a list of each meal, or this needs to split it up
        new GetDayFoodLogRequest(dateStr, ((GlobalClass) requireActivity().getApplicationContext()).getToken()).request(response -> {
            try {
                System.out.println(response.toString(3));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GetFoodLogResponse recipeResponse = Util.objFromJson(response, GetFoodLogResponse.class);
            if (recipeResponse == null) {
                Toaster.toastShort("Error getting recipes", requireContext());
                return;
            }

            SimpleFoodItem[] newItems = recipeResponse.getFoodLog();
            MainActivity.clearFoodLog();
            for (SimpleFoodItem item : newItems) {
                switch (item.getMeal()) {
                    case "Breakfast":
                        System.out.println(item.getMeal());
                        MainActivity.addLogItem(item, BREAKFAST_LOG);
                        break;
                    case "Lunch":
                        System.out.println(item.getMeal());
                        MainActivity.addLogItem(item, LUNCH_LOG);
                        break;
                    case "Dinner":
                        System.out.println(item.getMeal());
                        MainActivity.addLogItem(item, DINNER_LOG);
                        break;
                }
            }

        }, requireContext());



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
        refreshProgress(view);

        format = new SimpleDateFormat("EEE MMM, d, yyyy", Locale.getDefault());
        dateText.setText(format.format(date.getTime()));

        return view;
    }

    /**
     * Refreshes the nutrition log
     *
     * @param view view to find subviews of
     */
    public void refreshList(View view) {

        ArrayList<SimpleFoodItem> breakfast = MainActivity.getLog(BREAKFAST_LOG);
        ArrayList<SimpleFoodItem> lunch = MainActivity.getLog(LUNCH_LOG);
        ArrayList<SimpleFoodItem> dinner = MainActivity.getLog(DINNER_LOG);
        if (breakfast != null) {
            LinearLayout list = view.findViewById(R.id.breakfastList);
            for (int i = 0; i < breakfast.size(); i++) {
                SimpleFoodItem item = breakfast.get(i);
                addLogItem(item, list, view1 -> {
                    Intent intent = new Intent(getContext(), FoodDetailsActivity.class);
                    intent.putExtra(PARCEL_FOODITEM, item);
                    startActivity(intent);
                }, i == breakfast.size() - 1);
            }
            view.findViewById(R.id.breakfastCard).setVisibility(Objects.requireNonNull(breakfast).isEmpty() ? View.GONE : View.VISIBLE);
        } else {
            view.findViewById(R.id.breakfastCard).setVisibility(View.GONE);
        }
        if (lunch != null) {
            LinearLayout list = view.findViewById(R.id.lunchList);
            for (int i = 0; i < lunch.size(); i++) {
                SimpleFoodItem item = lunch.get(i);
                addLogItem(item, list, view1 -> {
                    Intent intent = new Intent(getContext(), FoodDetailsActivity.class);
                    intent.putExtra(PARCEL_FOODITEM, item);
                    startActivity(intent);
                }, i == lunch.size() - 1);
            }
            view.findViewById(R.id.lunchCard).setVisibility(Objects.requireNonNull(lunch).isEmpty() ? View.GONE : View.VISIBLE);
        } else {
            view.findViewById(R.id.lunchCard).setVisibility(View.GONE);
        }
        if (dinner != null) {
            LinearLayout list = view.findViewById(R.id.dinnerList);
            for (int i = 0; i < dinner.size(); i++) {
                SimpleFoodItem item = dinner.get(i);
                addLogItem(item, list, view1 -> {
                    Intent intent = new Intent(getContext(), FoodDetailsActivity.class);
                    intent.putExtra(PARCEL_FOODITEM, item);
                    startActivity(intent);
                }, i == dinner.size() - 1);
            }
            view.findViewById(R.id.dinnerCard).setVisibility(Objects.requireNonNull(dinner).isEmpty() ? View.GONE : View.VISIBLE);
        } else {
            view.findViewById(R.id.dinnerCard).setVisibility(View.GONE);
        }
    }

    /**
     * Refreshes the nutrition progress bars
     */
    public void refreshProgress(View view) {
        int totalCalories = 0;
        int totalFat = 0;
        int totalCarbs = 0;
        int totalProtein = 0;
        LinearProgressIndicator calories = view.findViewById(R.id.caloriesBar);
        LinearProgressIndicator fat = view.findViewById(R.id.fatBar);
        LinearProgressIndicator carbs = view.findViewById(R.id.carbBar);
        LinearProgressIndicator protein = view.findViewById(R.id.proteinBar);
        TextView caloriesLimit = view.findViewById(R.id.calLimit);
        TextView fatLimit = view.findViewById(R.id.fatLimit);
        TextView carbLimit = view.findViewById(R.id.carbLimit);
        TextView proteinLimit = view.findViewById(R.id.proteinLimit);
        TextView caloriesAmount = view.findViewById(R.id.calAmount);
        TextView fatAmount = view.findViewById(R.id.fatAmount);
        TextView carbAmount = view.findViewById(R.id.carbAmount);
        TextView proteinAmount = view.findViewById(R.id.proteinAmount);

        for (SimpleFoodItem item : MainActivity.getLog(BREAKFAST_LOG)) {
            totalCalories += item.getCalories();
            totalFat += item.getFat();
            totalCarbs += item.getCarbs();
            totalProtein += item.getProtein();
        }
        for (SimpleFoodItem item : MainActivity.getLog(LUNCH_LOG)) {
            totalCalories += item.getCalories();
            totalFat += item.getFat();
            totalCarbs += item.getCarbs();
            totalProtein += item.getProtein();
        }
        for (SimpleFoodItem item : MainActivity.getLog(DINNER_LOG)) {
            totalCalories += item.getCalories();
            totalFat += item.getFat();
            totalCarbs += item.getCarbs();
            totalProtein += item.getProtein();
        }

        caloriesLimit.setText("1600");
        fatLimit.setText("53");
        carbLimit.setText("200");
        proteinLimit.setText("80");

        caloriesAmount.setText(String.valueOf(totalCalories));
        fatAmount.setText(String.valueOf(totalFat));
        carbAmount.setText(String.valueOf(totalCarbs));
        proteinAmount.setText(String.valueOf(totalProtein));

        calories.setProgress(totalCalories);
        fat.setProgress(totalFat);
        carbs.setProgress(totalCarbs);
        protein.setProgress(totalProtein);
    }

    /**
     * Adds a log item to the given linear layout
     * @param item      Item to add
     * @param list      LinearLayout to add to
     * @param listener  OnClickListener for the item
     */
    public void addLogItem(SimpleFoodItem item, LinearLayout list, View.OnClickListener listener, boolean lastItem) {
        View view = View.inflate(getContext(), R.layout.ingredient_layout, null);
        ((TextView) view.findViewById(R.id.quantity)).setText(item.getBrand());
        ((TextView) view.findViewById(R.id.name)).setText(item.getDescription());
        view.setOnClickListener(listener);
        view.setPadding(dp8, dp8, dp8, dp8);
        list.addView(view);
        if (!lastItem) {
            MaterialDivider divider = new MaterialDivider(requireContext());
            list.addView(divider);
        }
    }
}
