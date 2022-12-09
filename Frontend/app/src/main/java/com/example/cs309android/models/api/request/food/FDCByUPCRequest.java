package com.example.cs309android.models.api.request.food;

import static com.example.cs309android.util.Constants.Urls.Food.QUERY_FOOD_DB;

import com.example.cs309android.models.ParameterizedRequestURL;
import com.example.cs309android.models.api.request.abstraction.GetRequest;
import com.google.gson.annotations.Expose;

/**
 * Request for getting an FDC id from a UPC code
 *
 * @author Mitch Hudson
 */
public class FDCByUPCRequest extends GetRequest {
    /**
     * Name of the item to search for
     */
    @Expose
    private final String upc;

    /**
     * Public constructor
     *
     * @param upc UPC code to look up
     */
    public FDCByUPCRequest(String upc) {
        this.upc = upc;
    }

    /**
     * Getter for the parameterized url
     *
     * @return Parameterized url
     */
    @Override
    public String getURL() {
        return new ParameterizedRequestURL(QUERY_FOOD_DB)
                .addPathVar(upc)
                .toString();
    }
}
