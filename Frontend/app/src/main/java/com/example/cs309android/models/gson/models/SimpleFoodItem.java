package com.example.cs309android.models.gson.models;

import static com.example.cs309android.util.Constants.ITEM_ID_NULL;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Simple food item used for displaying and moving data in the app
 *
 * @author Mitch Hudson
 */
public class SimpleFoodItem implements Parcelable {
    /**
     * FDC ID from the api
     * -1 if it is a Custom food item
     */
    @Expose
    private final int fdcId;
    /**
     * Database id for the custom foods table
     * -1 if it is a USDA food item
     */
    @Expose
    private final int dbId;
    /**
     * Description / Item name from api
     */
    @Expose
    private final String description;
    /**
     * Brand (if branded)
     */
    @Expose
    private final String brand;
    /**
     * True if the item should appear with strikeout on the shopping list
     */
    @Expose
    private boolean stricken;

    /**
     * Public constructor for new custom item
     * DB id will be assigned by backend
     *
     * @param description description / title
     * @param brand       Brand of the item (null for none)
     */
    public SimpleFoodItem(String description, String brand) {
        this.fdcId = ITEM_ID_NULL;
        this.dbId = ITEM_ID_NULL;
        this.description = description;
        this.brand = brand;
        this.stricken = false;
    }

    /**
     * Public constructor
     *
     * @param fdcId       fdc item id
     * @param dbId        custom item id
     * @param description description / title
     * @param brand       Brand of the item (null for none)
     */
    public SimpleFoodItem(int fdcId, int dbId, String description, String brand) {
        this.fdcId = fdcId;
        this.dbId = dbId;
        this.description = description;
        this.brand = brand;
        this.stricken = false;
    }

    /**
     * Public constructor
     * stricken is set
     *
     * @param fdcId       fdc item id
     * @param dbId        custom item id
     * @param description description / title
     * @param brand       Brand of the item (null for none)
     * @param stricken    true if the item should appear with strikeout on the shopping list
     */
    public SimpleFoodItem(int fdcId, int dbId, String description, String brand, boolean stricken) {
        this.fdcId = fdcId;
        this.dbId = dbId;
        this.description = description;
        this.brand = brand;
        this.stricken = stricken;
    }

    /**
     * Constructor from parcel
     *
     * @param in Parcel to unpack
     */
    protected SimpleFoodItem(Parcel in) {
        fdcId = in.readInt();
        dbId = in.readInt();
        description = in.readString();
        brand = in.readString();
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
     * Getter for the fdc id
     *
     * @return item id
     */
    public int getFdcId() {
        return fdcId;
    }

    /**
     * Getter for the custom id
     *
     * @return item id
     */
    public int getDbId() {
        return dbId;
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
     * Getter for the brand
     *
     * @return item brand / null
     */
    public String getBrand() {
        return brand;
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
        parcel.writeInt(dbId);
        parcel.writeString(description);
        parcel.writeString(brand);
        parcel.writeBoolean(stricken);
    }
}
