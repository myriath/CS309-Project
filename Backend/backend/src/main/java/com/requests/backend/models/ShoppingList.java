package com.requests.backend.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="shopping_list")
public class ShoppingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int shoppingId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "food_id", referencedColumnName = "id", nullable = false),
            @JoinColumn(name = "food_custom", referencedColumnName = "isCustom", nullable = false)
    })
    @Expose
    private SimpleFoodItem foodItem;
    @Expose
    private Boolean stricken;

    public ShoppingList() {

    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

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

    public int getShoppingId() {
        return shoppingId;
    }

    public void setShoppingId(int shoppingId) {
        this.shoppingId = shoppingId;
    }

    //    @Embeddable
//    public static class ShoppingListPK implements Serializable {
//        protected String username;
//        protected SimpleFoodItem.SimpleFoodItemPK foodItem;
//
//        public ShoppingListPK() {}
//
//        public ShoppingListPK(String username, SimpleFoodItem.SimpleFoodItemPK foodItem) {
//            this.username = username;
//            this.foodItem = foodItem;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            ShoppingListPK that = (ShoppingListPK) o;
//            return Objects.equals(username, that.username) && Objects.equals(foodItem, that.foodItem);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(username, foodItem);
//        }
//    }
}


