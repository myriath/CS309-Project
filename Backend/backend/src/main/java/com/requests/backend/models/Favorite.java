package com.requests.backend.models;

import javax.persistence.*;

@Entity
@Table(name="fav_foods")
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
