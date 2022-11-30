package com.requests.backend.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Simple food item used for displaying and moving data in the app
 *
 * @author Mitch Hudson
 */
@Entity
@Table(name = "simple_foods")
public class SimpleFoodItem implements Serializable {
    /**
     * FDC ID from the api or Custom Food ID
     */
    @Id
    private int id;

    /**
     * Description / Item name from api
     */
    private String description;
    /**
     * True if the item should appear with strikeout on the shopping list
     */
    private boolean stricken;

    @Id
    private boolean isCustom;

    public SimpleFoodItem() {}

    /**
     * Constructor for gson
     *
     * @param id       item id
     * @param description description / title
     */
    public SimpleFoodItem(int id, String description) {
        this.id = id;
        this.description = description;
        this.stricken = false;
    }

    /**
     * Constructor for gson
     *
     * @param id       item id
     * @param description description / title
     * @param stricken    true if the item should appear with strikeout on the shopping list
     */
    public SimpleFoodItem(int id, String description, boolean stricken) {
        this.id = id;
        this.description = description;
        this.stricken = stricken;
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

    public boolean isCustom() {
        return isCustom;
    }

    public void setCustom(boolean isCustom) {
        this.isCustom = isCustom;
    }

    @Override
    public String toString() {
        return "SimpleFoodItem{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", stricken=" + stricken +
                ", isCustom=" + isCustom +
                '}';
    }
}
