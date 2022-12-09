package com.example.cs309android.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cs309android.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * Custom view inflates the custom_nutrition_item layout.
 * For custom nutrition values in CustomFoodActivity
 *
 * @author Mitch Hudson
 */
public class CustomNutritionItemView extends FrameLayout {
    private TextInputLayout input;

    public CustomNutritionItemView(@NonNull Context context) {
        super(context);
    }

    public CustomNutritionItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomNutritionItemView, 0, 0);
        try {
            initView(
                    array.getString(R.styleable.CustomNutritionItemView_name),
                    array.getString(R.styleable.CustomNutritionItemView_hint));
        } finally {
            array.recycle();
        }
    }

    public CustomNutritionItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomNutritionItemView, defStyleAttr, 0);
        try {
            initView(
                    array.getString(R.styleable.CustomNutritionItemView_name),
                    array.getString(R.styleable.CustomNutritionItemView_hint));
        } finally {
            array.recycle();
        }
    }

    public CustomNutritionItemView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomNutritionItemView, defStyleAttr, defStyleRes);
        try {
            initView(
                    array.getString(R.styleable.CustomNutritionItemView_name),
                    array.getString(R.styleable.CustomNutritionItemView_hint));
        } finally {
            array.recycle();
        }
    }

    /**
     * Inflates the custom_nutrition_item layout under this view
     *
     * @param name Name of the nutrient
     * @param hint Hint for the editable text view
     */
    public void initView(String name, String hint) {
        View view = inflate(getContext(), R.layout.custom_nutrition_item, this);
        ((TextView) view.findViewById(R.id.name)).setText(name);
        input = view.findViewById(R.id.valueField);
        input.setHint(hint);
    }

    /**
     * Gets the value of the custom nutrient
     *
     * @return value of the nutrient
     */
    public float getValue() {
        return Float.parseFloat(Objects.requireNonNull(input.getEditText()).getText().toString());
    }

    /**
     * Sets the value of the custom nutrient
     *
     * @param value new value of the nutrient
     */
    public void setValue(float value) {
        Objects.requireNonNull(input.getEditText()).setText(String.valueOf(value));
    }
}
