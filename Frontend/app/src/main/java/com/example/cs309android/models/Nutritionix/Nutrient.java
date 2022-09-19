package com.example.cs309android.models.Nutritionix;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Nutrient{Object} returned by Nutritionix API.
 */
public class Nutrient implements Parcelable {
    public static HashMap<Integer, Nutrient> lookup;

    /**
     * An {Integer} representing the usda attribute id of the nutrient
     */
    private final Integer attr_id;
    /**
     * A {Float} representing the nutritional value of a nutrient
     */
    private final Double value;
    /**
     * A {String} as the nutrients unit of measurement
     */
    private final String unit;
    /**
     * A {String} as the nutrients proper name
     */
    private final String name;
    /**
     * A {String} as the nutrients usda given abbreviation
     */
    private final String usda_tag;

    public static Nutrient lookup(Integer attr_id) {
        if (attr_id == null || lookup == null) return null;
        return lookup.get(attr_id);
    }

    /**
     * Private constructor used when generating the lookup table
     *
     * @param attr_id  Id number used by Nutritionix API
     * @param value    Value for the nutrient
     * @param unit     Unit of the nutrient
     * @param name     Name of the nutrient
     * @param usda_tag USDA tag of the nutrient
     */
    private Nutrient(int attr_id, double value, String unit, String name, String usda_tag) {
        this.attr_id = attr_id;
        this.value = value;
        this.unit = unit;
        this.name = name;
        this.usda_tag = usda_tag;
    }

    /**
     * Public constructor from JSON object.
     *
     * @param attr_id id of the attribute to look up
     * @param value   value of the nutrient
     */
    public Nutrient(int attr_id, double value) {
        Nutrient nutrient = lookup(attr_id);

        this.attr_id = nutrient.getAttrId();
        this.value = value;
        unit = nutrient.getUnit();
        name = nutrient.getName();
        usda_tag = nutrient.getUsdaTag();
    }

    /**
     * Parcel constructor
     *
     * @param in Parcel to unpack
     */
    protected Nutrient(Parcel in) {
        attr_id = in.readInt();
        value = in.readDouble();
        unit = in.readString();
        name = in.readString();
        usda_tag = in.readString();
    }

    /**
     * Parcelable CREATOR
     * Used when parceling/unpacking
     */
    public static final Creator<Nutrient> CREATOR = new Creator<Nutrient>() {
        @Override
        public Nutrient createFromParcel(Parcel in) {
            return new Nutrient(in);
        }

        @Override
        public Nutrient[] newArray(int size) {
            return new Nutrient[size];
        }
    };

    /**
     * Getter for attr_id
     *
     * @return An {Integer} representing the usda attribute id of the nutrient
     */
    public int getAttrId() {
        return attr_id;
    }

    /**
     * Getter for value
     * @return A {Float} representing the nutritional value of a nutrient
     */
    public double getValue() {
        return value;
    }

    /**
     * Getter for unit
     * @return A {String} as the nutrients unit of measurement
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Getter for name
     * @return A {String} as the nutrients proper name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for usda_tag
     *
     * @return A {String} as the nutrients usda given abbreviation
     */
    public String getUsdaTag() {
        return usda_tag;
    }

    /**
     * Parcelable method
     *
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Writes this nutrient to a parcel
     *
     * @param parcel parcel to write to
     * @param i      flags
     */
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(attr_id);
        parcel.writeDouble(value);
        parcel.writeString(unit);
        parcel.writeString(name);
        parcel.writeString(usda_tag);
    }

    /**
     * Generates the lookup table.
     * Should be called at the start of the Main Activity.
     */
    public static void generateLookup() {
        if (lookup != null) return;
        lookup = new HashMap<>();
        lookup.put(301, new Nutrient(301, 0, "mg", "Calcium", "CA"));
        lookup.put(205, new Nutrient(205, 0, "g", "Carbohydrate, by difference", "CHOCDF"));
        lookup.put(601, new Nutrient(601, 0, "mg", "Cholesterol", "CHOLE"));
        lookup.put(208, new Nutrient(208, 0, "kcal", "Energy", "ENERC_KCAL"));
        lookup.put(606, new Nutrient(606, 0, "g", "Saturated fat", "FASAT"));
        lookup.put(204, new Nutrient(204, 0, "g", "Total fat", "FAT"));
        lookup.put(605, new Nutrient(605, 0, "g", "Total trans fat", "FATRN"));
        lookup.put(303, new Nutrient(303, 0, "mg", "Iron", "FE"));
        lookup.put(291, new Nutrient(291, 0, "g", "Total fiber", "FIBTG"));
        lookup.put(306, new Nutrient(306, 0, "mg", "Potassium", "K"));
        lookup.put(307, new Nutrient(307, 0, "mg", "Sodium", "NA"));
        lookup.put(203, new Nutrient(203, 0, "g", "Protein", "PROCNT"));
        lookup.put(269, new Nutrient(269, 0, "g", "Total sugar", "SUGAR"));
        lookup.put(539, new Nutrient(539, 0, "g", "Added sugars", "SUGAR_ADD"));
        lookup.put(324, new Nutrient(324, 0, "IU", "Vitamin D", "VITD"));
    }
}
