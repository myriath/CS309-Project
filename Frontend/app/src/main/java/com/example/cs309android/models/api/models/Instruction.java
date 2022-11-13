package com.example.cs309android.models.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.Comparator;

/**
 * Instruction class that stores the step number and text
 *
 * @author Mitch Hudson
 */
public class Instruction implements Parcelable {
    /**
     * Creator for Parcelable
     */
    public static final Creator<Instruction> CREATOR = new Creator<Instruction>() {
        @Override
        public Instruction createFromParcel(Parcel in) {
            return new Instruction(in);
        }

        @Override
        public Instruction[] newArray(int size) {
            return new Instruction[size];
        }
    };
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
     *
     * @param stepNum  Number for the instruction
     * @param stepText Text for the instruction
     */
    public Instruction(int stepNum, String stepText) {
        this.stepNum = stepNum;
        this.stepText = stepText;
    }

    /**
     * Parcel constructor
     *
     * @param in Parcel to unpack
     */
    protected Instruction(Parcel in) {
        stepNum = in.readInt();
        stepText = in.readString();
    }

    /**
     * Writes this object to a parcel
     *
     * @param dest  Parcel to write to
     * @param flags Flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(stepNum);
        dest.writeString(stepText);
    }

    /**
     * Unused
     *
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Getter for the step number
     *
     * @return Step number
     */
    public int getStepNum() {
        return stepNum;
    }

    /**
     * Getter for the step text
     *
     * @return Step text
     */
    public String getStepText() {
        return stepText;
    }

    /**
     * Sorts instructions by step number
     */
    static public class Sorter implements Comparator<Instruction> {
        @Override
        public int compare(Instruction instruction, Instruction t1) {
            return instruction.getStepNum() - t1.getStepNum();
        }
    }
}
