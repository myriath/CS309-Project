
package com.example.demo.rest;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "food_name",
    "image",
    "serving_unit",
    "nix_brand_id",
    "brand_name_item_name",
    "serving_qty",
    "nf_calories",
    "brand_name",
    "brand_type",
    "nix_item_id"
})
@Generated("jsonschema2pojo")
public class Branded {

    @JsonProperty("food_name")
    private String foodName;
    @JsonProperty("image")
    private Object image;
    @JsonProperty("serving_unit")
    private String servingUnit;
    @JsonProperty("nix_brand_id")
    private String nixBrandId;
    @JsonProperty("brand_name_item_name")
    private String brandNameItemName;
    @JsonProperty("serving_qty")
    private Integer servingQty;
    @JsonProperty("nf_calories")
    private Integer nfCalories;
    @JsonProperty("brand_name")
    private String brandName;
    @JsonProperty("brand_type")
    private Integer brandType;
    @JsonProperty("nix_item_id")
    private String nixItemId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("food_name")
    public String getFoodName() {
        return foodName;
    }

    @JsonProperty("food_name")
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @JsonProperty("image")
    public Object getImage() {
        return image;
    }

    @JsonProperty("image")
    public void setImage(Object image) {
        this.image = image;
    }

    @JsonProperty("serving_unit")
    public String getServingUnit() {
        return servingUnit;
    }

    @JsonProperty("serving_unit")
    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }

    @JsonProperty("nix_brand_id")
    public String getNixBrandId() {
        return nixBrandId;
    }

    @JsonProperty("nix_brand_id")
    public void setNixBrandId(String nixBrandId) {
        this.nixBrandId = nixBrandId;
    }

    @JsonProperty("brand_name_item_name")
    public String getBrandNameItemName() {
        return brandNameItemName;
    }

    @JsonProperty("brand_name_item_name")
    public void setBrandNameItemName(String brandNameItemName) {
        this.brandNameItemName = brandNameItemName;
    }

    @JsonProperty("serving_qty")
    public Integer getServingQty() {
        return servingQty;
    }

    @JsonProperty("serving_qty")
    public void setServingQty(Integer servingQty) {
        this.servingQty = servingQty;
    }

    @JsonProperty("nf_calories")
    public Integer getNfCalories() {
        return nfCalories;
    }

    @JsonProperty("nf_calories")
    public void setNfCalories(Integer nfCalories) {
        this.nfCalories = nfCalories;
    }

    @JsonProperty("brand_name")
    public String getBrandName() {
        return brandName;
    }

    @JsonProperty("brand_name")
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @JsonProperty("brand_type")
    public Integer getBrandType() {
        return brandType;
    }

    @JsonProperty("brand_type")
    public void setBrandType(Integer brandType) {
        this.brandType = brandType;
    }

    @JsonProperty("nix_item_id")
    public String getNixItemId() {
        return nixItemId;
    }

    @JsonProperty("nix_item_id")
    public void setNixItemId(String nixItemId) {
        this.nixItemId = nixItemId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
