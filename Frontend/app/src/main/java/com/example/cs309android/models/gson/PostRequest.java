package com.example.cs309android.models.gson;

import com.google.gson.GsonBuilder;

/**
 * Base class for post requests.
 * <p>
 * Post requests need a method to get the json body
 */
public class PostRequest {
    /**
     * Generates JSON of this object using GSON
     *
     * @return JSON string
     */
    public String getJSON() {
        return new GsonBuilder().serializeNulls().create().toJson(this);
    }
}
