
package com.requests.backend.foods;

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
    "serving_unit",
    "nix_brand_id",
    "serving_qty",
    "nf_calories",
    "brand_name",
    "uuid",
    "nix_item_id"
})
@Generated("jsonschema2pojo")
public class Self {

    @JsonProperty("food_name")
    private String foodName;
    @JsonProperty("serving_unit")
    private String servingUnit;
    @JsonProperty("nix_brand_id")
    private Object nixBrandId;
    @JsonProperty("serving_qty")
    private Double servingQty;
    @JsonProperty("nf_calories")
    private Double nfCalories;
    @JsonProperty("brand_name")
    private Object brandName;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("nix_item_id")
    private Object nixItemId;
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

    @JsonProperty("serving_unit")
    public String getServingUnit() {
        return servingUnit;
    }

    @JsonProperty("serving_unit")
    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }

    @JsonProperty("nix_brand_id")
    public Object getNixBrandId() {
        return nixBrandId;
    }

    @JsonProperty("nix_brand_id")
    public void setNixBrandId(Object nixBrandId) {
        this.nixBrandId = nixBrandId;
    }

    @JsonProperty("serving_qty")
    public Double getServingQty() {
        return servingQty;
    }

    @JsonProperty("serving_qty")
    public void setServingQty(Double servingQty) {
        this.servingQty = servingQty;
    }

    @JsonProperty("nf_calories")
    public Double getNfCalories() {
        return nfCalories;
    }

    @JsonProperty("nf_calories")
    public void setNfCalories(Double nfCalories) {
        this.nfCalories = nfCalories;
    }

    @JsonProperty("brand_name")
    public Object getBrandName() {
        return brandName;
    }

    @JsonProperty("brand_name")
    public void setBrandName(Object brandName) {
        this.brandName = brandName;
    }

    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("nix_item_id")
    public Object getNixItemId() {
        return nixItemId;
    }

    @JsonProperty("nix_item_id")
    public void setNixItemId(Object nixItemId) {
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
