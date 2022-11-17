package com.example.cs309android.models.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Simple recipe item used for displaying and moving data in the app
 *
 * @author Travis Massner
 * @author Mitch Hudson
 */
public class Recipe implements Parcelable {
    /**
     * Creator for Parcelable
     */
    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
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
     * Description of the recipe
     */
    @Expose
    private final String description;
    /**
     * Ingredient array of the recipe
     */
    @Expose
    private final Ingredient[] ingredients;
    /**
     * Instruction array of the recipe
     */
    @Expose
    private final Instruction[] instructions;
    /**
     * Creator of the recipe's username
     */
    @Expose
    private final String username;

    /**
     * Public constructor
     *
     * @param rid          Recipe id
     * @param recipeName   Recipe name
     * @param description  Description
     * @param ingredients  Ingredients
     * @param instructions Instructions
     * @param username     Creator's username
     */
    public Recipe(int rid, String recipeName, String description, Ingredient[] ingredients, Instruction[] instructions, String username) {
        this.rid = rid;
        this.recipeName = recipeName;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.username = username;
    }

    /**
     * Parcel constructor
     *
     * @param in Parcel to read from
     */
    protected Recipe(Parcel in) {
        rid = in.readInt();
        recipeName = in.readString();
        description = in.readString();
        ingredients = in.createTypedArray(Ingredient.CREATOR);
        instructions = in.createTypedArray(Instruction.CREATOR);
        username = in.readString();
    }

    /**
     * Creates a parcel from this object
     *
     * @param dest  Destination parcel
     * @param flags Flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(rid);
        dest.writeString(recipeName);
        dest.writeString(description);
        dest.writeTypedArray(ingredients, flags);
        dest.writeTypedArray(instructions, flags);
        dest.writeString(username);
    }

    /**
     * Getter for the recipe ID
     *
     * @return recipe ID
     */
    public int getRecipeID() {
        return rid;
    }

    /**
     * Getter for the description
     *
     * @return Description
     */
    public String getDescription() {
        return description;
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
     * Getter for the ingredients
     *
     * @return Ingredients
     */
    public Ingredient[] getIngredients() {
        return ingredients;
    }

    /**
     * Getter for the steps
     *
     * @return recipe steps
     */
    public Instruction[] getInstructions() {
        return instructions;
    }

    /**
     * Getter for the creator's username
     *
     * @return Creator's username
     */
    public String getUsername() {
        return username;
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
}
