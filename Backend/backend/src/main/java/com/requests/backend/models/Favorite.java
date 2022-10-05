package com.requests.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Favorite {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String username;
    private String food_name;

    private String fid;

    private Integer rank_val;



    public String getFoodName() {
        return food_name;
    }
}

