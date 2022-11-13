package com.requests.backend.models;

import com.requests.backend.models.composites.ShoppingListPK;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="shopping_list")
@IdClass(ShoppingListPK.class)
public class ShoppingList {
    @Id
    private String username;

    @Id
    private Integer id;

    private String description;

    private Boolean  stricken;

    private Boolean isCustom;

    public ShoppingList() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer fdcId) {
        this.id = fdcId;
    }

    public String getItemName() {
        return description;
    }

    public void setItemName(String itemName) {
        this.description = itemName;
    }

    public Boolean getStricken() {
        return stricken;
    }

    public void setStricken(Boolean stricken) {
        this.stricken = stricken;
    }

    public void setIsCustom(Boolean isCustom) { this.isCustom = isCustom; }

    public Boolean getIsCustom() { return isCustom; }
}


