package com.example.cs309android.fragments.nutrition;

import static com.example.cs309android.util.Constants.BREAKFAST_LOG;
import static com.example.cs309android.util.Constants.DINNER_LOG;
import static com.example.cs309android.util.Constants.LUNCH_LOG;
import static com.example.cs309android.util.Constants.PARCEL_FOODITEM;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        date = Calendar.getInstance();

        //THIS IS JUST TEST DATA
        MainActivity.addLogItem(new FoodLogItem("papajohn", "chicken"), BREAKFAST_LOG);
        MainActivity.addLogItem(new FoodLogItem("papajohn", "bacon"), BREAKFAST_LOG);
        MainActivity.addLogItem(new FoodLogItem("papajohn", "cheese"), BREAKFAST_LOG);
        MainActivity.addLogItem(new FoodLogItem("papajohn", "beef"), BREAKFAST_LOG);
        MainActivity.addLogItem(new FoodLogItem("papajohn", "pork"), LUNCH_LOG);
        MainActivity.addLogItem(new FoodLogItem("papajohn", "turkey"), LUNCH_LOG);
        MainActivity.addLogItem(new FoodLogItem("papajohn", "eggs"), DINNER_LOG);

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
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM, d, yyyy", Locale.getDefault());
        dateText.setText(format.format(date.getTime()));

        String dateStr = format.format(date.getTime());
        // TODO: Getter should get a list of each meal, or this needs to split it up
        new GetDayFoodLogRequest(dateStr, ((GlobalClass) requireActivity().getApplicationContext()).getToken()).request(response -> {
            try {
                System.out.print(response.toString(3));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GetFoodLogResponse recipeResponse = Util.objFromJson(response, GetFoodLogResponse.class);


            FoodLogItem[] newItems = recipeResponse.getFoodLog();
            MainActivity.setLog(newItems, BREAKFAST_LOG);

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

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateStr = format.format(date.getTime());
        // TODO: Getter should get a list of each meal, or this needs to split it up
        new GetDayFoodLogRequest(dateStr, ((GlobalClass) requireActivity().getApplicationContext()).getToken()).request(response -> {
            try {
                System.out.print(response.toString(3));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            GetFoodLogResponse recipeResponse = Util.objFromJson(response, GetFoodLogResponse.class);
            if (recipeResponse == null) {
                Toaster.toastShort("Error getting recipes", requireContext());
                return;
            }

            FoodLogItem[] newItems = recipeResponse.getFoodLog();
            MainActivity.setLog(newItems, BREAKFAST_LOG);

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
        ArrayList<FoodLogItem> breakfast = MainActivity.getLog(BREAKFAST_LOG);
        ArrayList<FoodLogItem> lunch = MainActivity.getLog(LUNCH_LOG);
        ArrayList<FoodLogItem> dinner = MainActivity.getLog(DINNER_LOG);
        if (breakfast != null) {
            LinearLayout list = view.findViewById(R.id.breakfastList);
            for (int i = 0; i < breakfast.size(); i++) {
                FoodLogItem item = breakfast.get(i);
                addLogItem(item, list, view1 -> {
                    Intent intent = new Intent(getContext(), FoodDetailsActivity.class);
//                    intent.putExtra(PARCEL_FOODITEM, item); // TODO: Make food log item parcelable
                    startActivity(intent);
                });
            }
            view.findViewById(R.id.breakfastCard).setVisibility(Objects.requireNonNull(breakfast).isEmpty() ? View.GONE : View.VISIBLE);
        } else {
            view.findViewById(R.id.breakfastCard).setVisibility(View.GONE);
        }
        if (lunch != null) {
            LinearLayout list = view.findViewById(R.id.lunchList);
            for (int i = 0; i < lunch.size(); i++) {
                FoodLogItem item = lunch.get(i);
                addLogItem(item, list, view1 -> {
                    Intent intent = new Intent(getContext(), FoodDetailsActivity.class);
//                    intent.putExtra(PARCEL_FOODITEM, item); // TODO: Make food log item parcelable
                    startActivity(intent);
                });
            }
            view.findViewById(R.id.lunchCard).setVisibility(Objects.requireNonNull(lunch).isEmpty() ? View.GONE : View.VISIBLE);
        } else {
            view.findViewById(R.id.lunchCard).setVisibility(View.GONE);
        }
        if (dinner != null) {
            LinearLayout list = view.findViewById(R.id.dinnerList);
            for (int i = 0; i < dinner.size(); i++) {
                FoodLogItem item = dinner.get(i);
                addLogItem(item, list, view1 -> {
                    Intent intent = new Intent(getContext(), FoodDetailsActivity.class);
//                    intent.putExtra(PARCEL_FOODITEM, item); // TODO: Make food log item parcelable
                    startActivity(intent);
                });
            }
            view.findViewById(R.id.dinnerCard).setVisibility(Objects.requireNonNull(dinner).isEmpty() ? View.GONE : View.VISIBLE);
        } else {
            view.findViewById(R.id.dinnerCard).setVisibility(View.GONE);
        }
    }

    public void addLogItem(FoodLogItem item, LinearLayout list, View.OnClickListener listener) {
        TextView title = new TextView(requireContext());
        title.setText(item.getFoodName());
        int dp16 = (int) Util.scalePixels(16);
        title.setPadding(dp16, dp16, dp16, dp16);
        title.setOnClickListener(listener);
        list.addView(title);
        MaterialDivider divider = new MaterialDivider(requireContext());
        list.addView(divider);
    }
}
