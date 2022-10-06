package com.example.cs309android.models.gson.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

/**
 * Simple recipe item used for displaying and moving data in the app
 */
public class SimpleRecipeItem implements Parcelable {
    /**
     * ID of recipe stored in database
     */
    @Expose
    private final int recipeID;
    /**
     * Name of the recipe
     */
    @Expose
    private final String recipeName;
    /**
     * Name of the recipe
     */
    @Expose
    private final String steps;

//UNCOMMENT WHEN YOU WANT TO USE ARRAYLIST FOR STEPS INSTEAD OF STRING
//    /**
//     * Steps of the recipe
//     */
//    @Expose
//    private final ArrayList<String> steps;

    /**
     * Constructor for gson
     *
     * @param recipeName       item id
     * @param steps steps of recipe
     */
    public SimpleRecipeItem(int recipeID, String recipeName, String steps) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.steps = steps;
    }


    /**
     * Constructor from parcel
     *
     * @param in Parcel to unpack
     */
    protected SimpleRecipeItem(Parcel in) {
        recipeID = in.readInt();
        recipeName = in.readString();
        steps = in.readString();
//        steps = in.createStringArrayList();
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
        return recipeID;
    }
    /**
     * Getter for the recipe name
     *
     * @return recipe name
     */
    public String getRecipeName() {
        return recipeName;
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
        parcel.writeInt(recipeID);
        parcel.writeString(recipeName);
        parcel.writeString(steps);
//        parcel.writeArray(steps.toArray(new String[steps.size()]));
    }
}
