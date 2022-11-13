package com.example.cs309android.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cs309android.R;
import com.example.cs309android.models.api.models.Instruction;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

/**
 * Custom view inflates the nutrition_item layout.
 * For nutrition list in FoodDetails
 *
 * @author Mitch Hudson
 */
public class InstructionEditView extends FrameLayout {
    /**
     * View for finding sub-views
     */
    private View view;
    /**
     * Position in the list view
     */
    private int position;

    public InstructionEditView(@NonNull Context context) {
        super(context);
    }

    public InstructionEditView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InstructionEditView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public InstructionEditView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * Inflates the nutrition_item layout under this view
     *
     * @param listener Runs when the remove button is pressed
     * @param position Keeps track of the instruction number
     */
    public void initView(OnClickListener listener, int position) {
        view = inflate(getContext(), R.layout.instruction_add, this);
        view.findViewById(R.id.remove).setOnClickListener(listener);
        setPosition(position);
    }

    /**
     * Getter for the instruction text
     *
     * @return instruction text
     */
    public String getInstructionText() {
        return Objects.requireNonNull(((TextInputEditText) view.findViewById(R.id.editText)).getText()).toString();
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
        ((TextInputLayout) view.findViewById(R.id.inputLayout)).setHint("Step " + (position + 1) + ".");
        this.position = position;
    }

    /**
     * Gets the instruction this view builds
     *
     * @return Instruction representing this view
     */
    public Instruction getInstruction() {
        return new Instruction(position, getInstructionText());
    }
}
