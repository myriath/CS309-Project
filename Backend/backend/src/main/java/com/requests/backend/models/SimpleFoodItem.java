package com.requests.backend.models;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * Simple food item used for displaying and moving data in the app
 *
 * @author Mitch Hudson
 */
@Entity
@IdClass(SimpleFoodItem.SimpleFoodItemPK.class)
@Table(name = "simple_foods")
public class SimpleFoodItem {
    //    /**
    //     * FDC ID from the api or Custom Food ID
    //     */
    @Id
    @Expose
    private int id;

    /**
     * Description / Item name from api
     */
    @Expose
    private String description;

    @Expose
    private boolean isCustom;

    @OneToMany(mappedBy = "food")
    private Set<Ingredient> ingredients;

    public SimpleFoodItem() {}

    /**
     * Getter for the id
     *
     * @return item id
     */
    public int getId() {
        return this.id;
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

    public boolean isCustom() {
        return this.isCustom;
    }

    public void setCustom(boolean isCustom) {
        this.isCustom = isCustom;
    }

    @Override
    public String toString() {
        return "SimpleFoodItem{" +
                "id=" + this.id +
                ", description='" + description + '\'' +
                ", isCustom=" +  this.isCustom +
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
