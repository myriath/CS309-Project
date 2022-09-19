
package com.example.demo.rest;

import java.util.HashMap;
import java.util.List;
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
    "branded",
    "self",
    "common"
})
@Generated("jsonschema2pojo")
public class Food {

    @JsonProperty("branded")
    private List<Branded> branded = null;
    @JsonProperty("self")
    private List<Self> self = null;
    @JsonProperty("common")
    private List<Common> common = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("branded")
    public List<Branded> getBranded() {
        return branded;
    }

    @JsonProperty("branded")
    public void setBranded(List<Branded> branded) {
        this.branded = branded;
    }

    @JsonProperty("self")
    public List<Self> getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(List<Self> self) {
        this.self = self;
    }

    @JsonProperty("common")
    public List<Common> getCommon() {
        return common;
    }

    @JsonProperty("common")
    public void setCommon(List<Common> common) {
        this.common = common;
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
