package com.example.cs309android.models.gson.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Simple recipe item used for displaying and moving data in the app
 *
 * @author Travis Massner
 */
public class SimpleRecipeItem implements Parcelable {
    /**
     * ID of recipe stored in database
     */
    @Expose
    private final int rid;
    /**
     * Name of the recipe
     */
    @Expose
    private final String rname;
    /**
     * Name of the recipe
     */
    @Expose
    private String steps;

    /**
     * Constructor for gson
     * @param rid    Recipe id
     * @param rname  Recipe Name
     * @param steps  Steps of recipe
     */
    public SimpleRecipeItem(int rid, String rname, String steps) {
        this.rid = rid;
        this.rname = rname;
        this.steps = steps;
    }

    /**
     * Constructor for gson
     * @param rid     Recipe id
     * @param rname    Recipe Name
     */
    public SimpleRecipeItem(int rid, String rname) {
        this.rid = rid;
        this.rname = rname;
    }


    /**
     * Constructor from parcel
     *
     * @param in Parcel to unpack
     */
    protected SimpleRecipeItem(Parcel in) {
        rid = in.readInt();
        rname = in.readString();
        steps = in.readString();
    }

    /**
     * Parcelable required CREATOR object
     */
    public static final Parcelable.Creator<SimpleRecipeItem> CREATOR = new Parcelable.Creator<SimpleRecipeItem>() {
        @Override
        public SimpleRecipeItem createFromParcel(Parcel in) {
            return new SimpleRecipeItem(in);
        }

        @Override
        public SimpleRecipeItem[] newArray(int size) {
            return new SimpleRecipeItem[size];
        }
    };

    /**
     * Getter for the recipe ID
     *
     * @return recipe ID
     */
    public int getRecipeID() {
        return rid;
    }
    /**
     * Getter for the recipe name
     *
     * @return recipe name
     */
    public String getRecipeName() {
        return rname;
    }

    /**
     * Getter for the steps
     *
     * @return recipe steps
     */
    public String getSteps() {
        return steps;
    }


    /**
     * Parcelable required method
     *
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Adds this object to a parcel
     *
     * @param parcel parcel to add this object to
     * @param i      flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(rid);
        parcel.writeString(rname);
        parcel.writeString(steps);
    }
}
