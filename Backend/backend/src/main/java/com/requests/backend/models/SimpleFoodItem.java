package com.requests.backend.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Simple food item used for displaying and moving data in the app
 *
 * @author Mitch Hudson
 */
@Entity
@Table(name = "simple_foods")
@IdClass(SimpleFoodItem.SimpleFoodItemPK.class)
public class SimpleFoodItem {
    /**
     * FDC ID from the api or Custom Food ID
     */
    @Id
    @Expose
    private int id;

    /**
     * Description / Item name from api
     */
    @Expose
    private String description;
    /**
     * True if the item should appear with strikeout on the shopping list
     */
    @Expose
    @Transient
    private boolean stricken = false;

    @Id
    @Expose
    private boolean isCustom;

    public SimpleFoodItem() {}

    /**
     * Getter for the id
     *
     * @return item id
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public static class SimpleFoodItemPK implements Serializable {
        protected int id;
        protected boolean isCustom;

        public SimpleFoodItemPK() {}

        public SimpleFoodItemPK(int id, boolean isCustom) {
            this.isCustom = isCustom;
            this.id = id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, isCustom);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            SimpleFoodItemPK other = (SimpleFoodItemPK) obj;
            return other.isCustom == isCustom && other.id == id;
        }
    }
}
