package com.example.cs309android.fragments.nutrition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.cs309android.R;
import com.example.cs309android.fragments.BaseFragment;
import com.example.cs309android.models.adapters.HomeItemAdapter;
import com.example.cs309android.models.adapters.NutritionLogAdapter;
import com.example.cs309android.models.gson.models.SimpleFoodItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NutritionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NutritionFragment extends BaseFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Calendar date;
    ImageButton leftButton;
    ImageButton rightButton;
    NutritionLogAdapter adapter;
    ListView listView;
    ArrayList<SimpleFoodItem> foods;

    public NutritionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NutritionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NutritionFragment newInstance(String param1, String param2) {
        NutritionFragment fragment = new NutritionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        date = Calendar.getInstance();

        foods = new ArrayList<>();
        SimpleFoodItem foodLog = new SimpleFoodItem("Chicken", "Walmart");
        foods.add(foodLog);
        foodLog = new SimpleFoodItem("Turkey", "Target");
        foods.add(foodLog);
        foodLog = new SimpleFoodItem("Beef", "Shopko");
        foods.add(foodLog);
        foodLog = new SimpleFoodItem("Pork", "Kroger");
        foods.add(foodLog);

    }

    private void updateDate() {
        TextView dateText = getView().findViewById(R.id.date);
        SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d, yyyy");
        dateText.setText(format.format(date.getTime()));
    }

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

        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date.add(Calendar.DATE, -1);
                updateDate();
            }
        });

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date.add(Calendar.DATE, 1);
                updateDate();
            }
        });

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = Calendar.getInstance();
                updateDate();
            }
        });

        adapter = new NutritionLogAdapter(this.getActivity(), foods);
        listView = view.findViewById(R.id.log_list);
        listView.setAdapter(adapter);

        SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d, yyyy");
        dateText.setText(format.format(date.getTime()));

        return view;
    }
}