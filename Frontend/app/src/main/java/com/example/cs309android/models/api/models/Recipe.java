package com.example.cs309android.models.api.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;

import java.util.Arrays;

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
     * Creator of the recipe's username
     */
    @Expose
    private final User user;
    /**
     * Name of the recipe
     */
    @Expose
    private final String rname;
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
     * Array of comments on the recipe
     */
    @Expose
    private final Comment[] comments;

    /**
     * Public constructor
     *
     * @param rid          Recipe id
     * @param rname        Recipe name
     * @param description  Description
     * @param ingredients  Ingredients
     * @param instructions Instructions
     * @param user         Creator's username
     * @param comments     Comments on this recipe
     */
    public Recipe(int rid, String rname, String description, Ingredient[] ingredients, Instruction[] instructions, User user, Comment[] comments) {
        this.rid = rid;
        this.rname = rname;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.user = user;
        this.comments = comments;
    }

    /**
     * Parcel constructor
     *
     * @param in Parcel to read from
     */
    protected Recipe(Parcel in) {
        rid = in.readInt();
        rname = in.readString();
        description = in.readString();
        ingredients = in.createTypedArray(Ingredient.CREATOR);
        instructions = in.createTypedArray(Instruction.CREATOR);
        user = in.readTypedObject(User.CREATOR);
        comments = in.createTypedArray(Comment.CREATOR);
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
        dest.writeString(rname);
        dest.writeString(description);
        dest.writeTypedArray(ingredients, flags);
        dest.writeTypedArray(instructions, flags);
        dest.writeTypedObject(user, flags);
        dest.writeTypedArray(comments, flags);
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
    public String getRname() {
        return rname;
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
     * Getter for the creator user
     *
     * @return Creator user
     */
    public User getUser() {
        return user;
    }

    /**
     * Getter for the recipe's comments
     *
     * @return Recipe's comments
     */
    public Comment[] getComments() {
        return comments;
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

    @NonNull
    @Override
    public String toString() {
        return "Recipe{" +
                "rid=" + rid +
                ", username='" + user.toString() + '\'' +
                ", rname='" + rname + '\'' +
                ", description='" + description + '\'' +
                ", ingredients=" + Arrays.toString(ingredients) +
                ", instructions=" + Arrays.toString(instructions) +
                ", comments=" + Arrays.toString(comments) +
                '}';
    }
}
