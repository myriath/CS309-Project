
package com.requests.backend.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class SearchFood {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String fdc_id;
    private String data_type;
    private String description;
    private String food_category_id;
    private String publication_date;
    private String score;

    public String getFdc_id() {
        return fdc_id;
    }

    public void setFdc_id(String fdc_id) {
        this.fdc_id = fdc_id;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFood_category_id() {
        return food_category_id;
    }

    public void setFood_category_id(String food_category_id) {
        this.food_category_id = food_category_id;
    }

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
