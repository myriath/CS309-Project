package com.example.cs309android.models.USDA.custom;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Simple food item used for displaying and moving data in the app
 *
 * @author Mitch Hudson
 */
public class SimpleFoodItem implements Parcelable {
    /**
     * FDC ID from the api
     */
    private final int fdcId;
    /**
     * Description / Item name from api
     */
    private final String description;
    /**
     * True if the item should appear with strikeout on the shopping list
     */
    private boolean stricken = false;

    /**
     * Constructor for gson
     *
     * @param fdcId       item id
     * @param description description / title
     */
    public SimpleFoodItem(int fdcId, String description) {
        this.fdcId = fdcId;
        this.description = description;
    }

    /**
     * Constructor for gson
     *
     * @param fdcId       item id
     * @param description description / title
     * @param stricken    true if the item should appear with strikeout on the shopping list
     */
    public SimpleFoodItem(int fdcId, String description, boolean stricken) {
        this.fdcId = fdcId;
        this.description = description;
        this.stricken = stricken;
    }

    /**
     * Constructor from parcel
     *
     * @param in Parcel to unpack
     */
    protected SimpleFoodItem(Parcel in) {
        fdcId = in.readInt();
        description = in.readString();
        stricken = in.readBoolean();
    }

    /**
     * Parcelable required CREATOR object
     */
    public static final Creator<SimpleFoodItem> CREATOR = new Creator<SimpleFoodItem>() {
        @Override
        public SimpleFoodItem createFromParcel(Parcel in) {
            return new SimpleFoodItem(in);
        }

        @Override
        public SimpleFoodItem[] newArray(int size) {
            return new SimpleFoodItem[size];
        }
    };

    /**
     * Getter for the id
     *
     * @return item id
     */
    public int getFdcId() {
        return fdcId;
    }

    /**
     * Getter for the description
     *
     * @return item description / title
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter for the stricken boolean
     *
     * @return true if this item should be rendered with strikeout
     */
    public boolean isStricken() {
        return stricken;
    }

    /**
     * Setter for the stricken boolean
     *
     * @param stricken true if this item should be rendered with strikeout
     */
    public void setStricken(boolean stricken) {
        this.stricken = stricken;
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
        parcel.writeInt(fdcId);
        parcel.writeString(description);
        parcel.writeBoolean(stricken);
    }
}
