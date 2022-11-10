package com.example.cs309android.models.gson.models;

import com.google.gson.annotations.Expose;

/**
 * Instruction class that stores the step number and text
 *
 * @author Mitch Hudson
 */
public class Instruction {
    /**
     * Step number for this instruction
     */
    @Expose
    private final int stepNum;
    /**
     * Step text for this instruction
     */
    @Expose
    private final String stepText;

    /**
     * Public constructor
     * @param stepNum   Number for the instruction
     * @param stepText  Text for the instruction
     */
    public Instruction(int stepNum, String stepText) {
        this.stepNum = stepNum;
        this.stepText = stepText;
    }

    /**
     * Getter for the step number
     * @return Step number
     */
    public int getStepNum() {
        return stepNum;
    }

    /**
     * Getter for the step text
     * @return Step text
     */
    public String getStepText() {
        return stepText;
    }
}
