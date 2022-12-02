package com.requests.backend.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="shopping_list")
@IdClass(ShoppingList.ShoppingListPK.class)
public class ShoppingList {
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    @Expose
    private User user;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private SimpleFoodItem foodItem;
    @Expose
    private Boolean stricken;

    public ShoppingList() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getStricken() {
        return stricken;
    }

    public void setStricken(Boolean stricken) {
        this.stricken = stricken;
    }

    public SimpleFoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(SimpleFoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public static class ShoppingListPK implements Serializable {
        protected User user;
        protected SimpleFoodItem foodItem;

        public ShoppingListPK() {}

        public ShoppingListPK(User user, SimpleFoodItem foodItem) {
            this.user = user;
            this.foodItem = foodItem;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ShoppingListPK that = (ShoppingListPK) o;
            return Objects.equals(user, that.user) && Objects.equals(foodItem, that.foodItem);
        }

        @Override
        public int hashCode() {
            return Objects.hash(user, foodItem);
        }
    }
}


