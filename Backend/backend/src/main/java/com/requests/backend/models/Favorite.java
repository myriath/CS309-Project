package com.requests.backend.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name="fav_foods")
public class Favorite {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Expose
    private String username;
    @Expose
    private String food_name;
    @Expose
    private String fid;
    @Expose
    private Integer rank_val;



    public String getFoodName() {
        return food_name;
    }
}
