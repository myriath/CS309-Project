package com.example.cs309android.models.api.request;

import static com.example.cs309android.util.Constants.IMAGE_URL;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetImageRequest;

/**
 * Abstract image get request handler
 *
 * @author Mitch Hudson
 */
public class GetImageByIdRequest extends GetImageRequest {
    /**
     * Image id
     */
    private final String id;

    /**
     * Constructor
     *
     * @param id Image id for the image to load
     */
    public GetImageByIdRequest(String id) {
        this.id = id;
    }

    /**
     * Gets the image id
     *
     * @return image id
     */
    public String getUsername() {
        return id;
    }

    /**
     * Getter for the url for this get request
     *
     * @return URL for the request
     */
    public String getURL() {
        return new ParameterizedRequestURL(IMAGE_URL + id).toString();
    }
}
