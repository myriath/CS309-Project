package com.example.cs309android.models.gson.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.cs309android.util.Constants;
import com.google.gson.annotations.Expose;

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
    private final String recipeName;
    /**
     * Name of the recipe
     */
    @Expose
    private final String instructions;

    /**
     * Constructor for gson
     * @param rid    Recipe id
     * @param recipeName  Recipe Name
     * @param instructions  Steps of recipe
     */
    public SimpleRecipeItem(int rid, String recipeName, String instructions) {
        this.rid = rid;
        this.recipeName = recipeName;
        this.instructions = instructions;
    }

    /**
     * Constructor for gson
     * @param rid     Recipe id
     * @param recipeName    Recipe Name
     */
    public SimpleRecipeItem(int rid, String recipeName) {
        this.rid = rid;
        this.recipeName = recipeName;
        this.instructions = "";
    }

    /**
     * Constructor for gson
     * @param instructions     Recipe instructions
     * @param recipeName    Recipe Name
     */
    public SimpleRecipeItem(String recipeName, String instructions) {
        this.rid = Constants.ITEM_ID_NULL;
        this.recipeName = recipeName;
        this.instructions = instructions;
    }


    /**
     * Constructor from parcel
     *
     * @param in Parcel to unpack
     */
    protected SimpleRecipeItem(Parcel in) {
        rid = in.readInt();
        recipeName = in.readString();
        instructions = in.readString();
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
        return recipeName;
    }

    /**
     * Getter for the steps
     *
     * @return recipe steps
     */
    public String getInstructions() {
        return instructions;
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
        parcel.writeString(recipeName);
        parcel.writeString(instructions);
    }
}
