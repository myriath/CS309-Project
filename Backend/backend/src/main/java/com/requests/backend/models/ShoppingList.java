package com.requests.backend.models;

import com.google.gson.annotations.Expose;
import com.requests.backend.models.composites.ShoppingListPK;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="shopping_list")
@IdClass(ShoppingListPK.class)
public class ShoppingList {
    @Id
    private String username;

    @Expose
    private Integer fdcId;

    @Id
    @Expose
    private String itemName;

    @Expose
    private Boolean  stricken;

    public ShoppingList() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getFdcId() {
        return fdcId;
    }

    public void setFdcId(Integer fdcId) {
        this.fdcId = fdcId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Boolean getStricken() {
        return stricken;
    }

    public void setStricken(Boolean stricken) {
        this.stricken = stricken;
    }
}


