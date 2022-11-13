package com.example.cs309android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cs309android.R;

/**
 * Custom view inflates the nutrition_item layout.
 * For nutrition list in FoodDetails
 *
 * @author Mitch Hudson
 */
public class NutritionItemView extends FrameLayout {
    public NutritionItemView(@NonNull Context context) {
        super(context);
    }

    public NutritionItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NutritionItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NutritionItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Inflates the nutrition_item layout under this view
     *
     * @param name   Name of the nutrient
     * @param amount String of the amount of the nutrient
     */
    public void initView(String name, String amount) {
        View view = inflate(getContext(), R.layout.nutrition_item, this);
        ((TextView) view.findViewById(R.id.name)).setText(name);
        ((TextView) view.findViewById(R.id.value)).setText(amount);
    }
}
