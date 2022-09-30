
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
    "image",
    "tag_id",
    "tag_name"
})
@Generated("jsonschema2pojo")
public class Common {

    @JsonProperty("food_name")
    private String foodName;
    @JsonProperty("image")
    private Object image;
    @JsonProperty("tag_id")
    private String tagId;
    @JsonProperty("tag_name")
    private String tagName;
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

    @JsonProperty("tag_id")
    public String getTagId() {
        return tagId;
    }

    @JsonProperty("tag_id")
    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    @JsonProperty("tag_name")
    public String getTagName() {
        return tagName;
    }

    @JsonProperty("tag_name")
    public void setTagName(String tagName) {
        this.tagName = tagName;
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
