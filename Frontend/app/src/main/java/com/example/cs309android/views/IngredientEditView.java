package com.example.cs309android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cs309android.R;
import com.example.cs309android.models.api.models.Ingredient;
import com.example.cs309android.models.api.models.SimpleFoodItem;

/**
 * Custom view inflates the nutrition_item layout.
 * For nutrition list in FoodDetails
 *
 * @author Mitch Hudson
 */
public class IngredientEditView extends FrameLayout {
    /**
     * Item this view represents
     */
    private SimpleFoodItem item;
    /**
     * View for finding sub-views
     */
    private View view;
    /**
     * Unit for the ingredient
     */
    private String unit;
    /**
     * Position in the list
     */
    private int position;

    public IngredientEditView(@NonNull Context context) {
        super(context);
    }

    public IngredientEditView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IngredientEditView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public IngredientEditView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Inflates the nutrition_item layout under this view
     *
     * @param item     Item for the ingredient
     * @param unit     Unit for the quantity
     * @param listener Runs when the remove button is pressed
     * @param position Position in the list
     */
    public void initView(SimpleFoodItem item, String unit, OnClickListener listener, int position) {
        view = inflate(getContext(), R.layout.ingredient_add, this);
        ((TextView) view.findViewById(R.id.name)).setText(item.getCappedDescription(25));
        ((TextView) view.findViewById(R.id.unit)).setText(unit);
        view.findViewById(R.id.remove).setOnClickListener(listener);
        this.item = item;
        this.unit = unit;
        this.position = position;
    }

    /**
     * Getter for the quantity of the ingredient
     *
     * @return quantity
     */
    public float getQuantity() {
        return Float.parseFloat(((EditText) view.findViewById(R.id.quantity)).getText().toString());
    }

    /**
     * Getter for the position
     *
     * @return position of the ingredient
     */
    public int getPosition() {
        return position;
    }

    /**
     * Setter for the position
     *
     * @param position position of the ingredient
     */
    public void setPosition(int position) {
        this.position = position;
    }

    public Ingredient getIngredient() {
        return new Ingredient(item, getQuantity(), unit);
    }
}
