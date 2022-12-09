package com.example.cs309android.models.api.models;

import static com.example.cs309android.util.Constants.ITEM_ID_NULL;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.cs309android.models.USDA.models.BrandedFoodItem;
import com.google.gson.annotations.Expose;

/**
 * Simple food item used for displaying and moving data in the app
 *
 * @author Mitch Hudson
 */
public class SimpleFoodItem implements Parcelable {
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
     * FDC ID from the api
     * -1 if it is a Custom food item
     */
    @Expose
    private final int id;
    /**
     * True for custom, false for fdcId
     */
    @Expose
    private final boolean isCustom;
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

    //ALL OF THESE ARE FOR THE NUTRITION PAGE
    /**
     * Calories of the item
     */
    @Expose
    private float calories;

    /**
     * Fat of the item
     */
    @Expose
    private float fat;

    /**
     * Carbs of the item
     */
    @Expose
    private float carbs;

    /**
     * Protein of the item
     */
    @Expose
    private float protein;

    /**
     * Meal type of the item
     */
    @Expose
    private String meal;

    /**
     * Date of the item
     */
    @Expose
    private String date;


    /**
     * Public constructor for new food log item
     * @param id          item id
     * @param description description / title
     * @param brand       Brand of the item (if branded)
     * @param calories    Calories of the item
     * @param fat         Fat of the item
     * @param carbs       Carbs of the item
     * @param protein     Protein of the item
     */
    public SimpleFoodItem(int id, String description, String brand, float calories, float fat, float carbs, float protein, String meal, boolean isCustom) {
        this.id = id;
        this.description = description;
        this.brand = brand;
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
        this.meal = meal;
        this.isCustom = isCustom;
    }


    /**
     * Public constructor for new custom item
     * DB id will be assigned by backend
     *
     * @param description description / title
     * @param brand       Brand of the item (null for none)
     */
    public SimpleFoodItem(String description, String brand) {
        this.id = ITEM_ID_NULL;
        this.description = description;
        this.brand = brand;
        this.stricken = false;
        this.isCustom = true;
    }

    /**
     * Public constructor
     *
     * @param id          item id
     * @param description description / title
     * @param brand       Brand of the item (null for none)
     * @param isCustom    True if the id is dbId, false if it is fdcId
     */
    public SimpleFoodItem(int id, String description, String brand, boolean isCustom) {
        this.id = id;
        this.description = description;
        this.brand = brand;
        this.stricken = false;
        this.isCustom = isCustom;
    }

    /**
     * Public constructor
     * stricken is set
     *
     * @param id          item id
     * @param description description / title
     * @param brand       Brand of the item (null for none)
     * @param stricken    true if the item should appear with strikeout on the shopping list
     */
    public SimpleFoodItem(int id, String description, String brand, boolean stricken, boolean isCustom) {
        this.id = id;
        this.description = description;
        this.brand = brand;
        this.stricken = stricken;
        this.isCustom = isCustom;
    }

    /**
     * Constructor from parcel
     *
     * @param in Parcel to unpack
     */
    protected SimpleFoodItem(Parcel in) {
        id = in.readInt();
        description = in.readString();
        brand = in.readString();
        calories = in.readFloat();
        fat = in.readFloat();
        carbs = in.readFloat();
        protein = in.readFloat();
        stricken = in.readBoolean();
        isCustom = in.readBoolean();
    }

    /**
     * Getter for the id
     *
     * @return item id
     */
    public int getId() {
        return id;
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
     * Gets the description capped at a given length
     * If the description is longer than length it will be shortened to that and have ellipses added.
     *
     * @param length Max length for the description.
     * @return Capped length string
     */
    public String getCappedDescription(int length) {
        if (length < 3) return description;

        int descriptionLength = description.length();
        if (descriptionLength > length) {
            return description.substring(0, length - 2) + "...";
        }
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
     * Gets the isCustom bool
     *
     * @return True if the item uses dbIds, false if it uses fdcId
     */
    public boolean isCustom() {
        return isCustom;
    }

    /**
     * Gets the calories
     */
    public float getCalories() {
        return calories;
    }

    /**
     * Gets the fat
     */
    public float getFat() {
        return fat;
    }

    /**
     * Gets the carbs
     */
    public float getCarbs() {
        return carbs;
    }

    /**
     * Gets the protein
     */
    public float getProtein() {
        return protein;
    }

    /**
     * Gets the meal type
     */
    public String getMeal() {
        return meal;
    }

    /**
     * Gets the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the nutrients variables for a simple food item
     */
    public void setNutrients(BrandedFoodItem.LabelNutrients nutrients) {
        this.calories = nutrients.getCalories().getValue();
        this.fat = nutrients.getFat().getValue();
        this.carbs = nutrients.getCarbohydrates().getValue();
        this.protein = nutrients.getProtein().getValue();
    }

    /**
     * Set the meal and date of the simple food item for food log
     */
    public void setMealAndDate(String meal, String date) {
        this.meal = meal;
        this.date = date;
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
        parcel.writeInt(id);
        parcel.writeString(description);
        parcel.writeString(brand);
        parcel.writeFloat(calories);
        parcel.writeFloat(fat);
        parcel.writeFloat(carbs);
        parcel.writeFloat(protein);
        parcel.writeBoolean(stricken);
        parcel.writeBoolean(isCustom);
    }

    @NonNull
    @Override
    public String toString() {
        return "SimpleFoodItem{" +
                "id=" + id +
                ", isCustom=" + isCustom +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", stricken=" + stricken +
                ", calories=" + calories +
                ", fat=" + fat +
                ", carbs=" + carbs +
                ", protein=" + protein +
                '}';
    }
}
