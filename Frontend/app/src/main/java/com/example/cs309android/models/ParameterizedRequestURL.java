package com.example.cs309android.models;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class used to encode parameters to a valid Get request url.
 *
 * @author Mitch Hudson
 */
public class ParameterizedRequestURL {
    /**
     * ArrayList of parameters to encode into the url
     */
    private final ArrayList<RequestParam> params;
    /**
     * Base url (no '?')
     * if null, the returned string will just be the encoded parameters.
     */
    private final StringBuilder url;

    /**
     * Constructor for a new GetRequestURL that only outputs encoded parameters.
     */
    public ParameterizedRequestURL() {
        this.params = new ArrayList<>();
        this.url = null;
    }

    /**
     * Constructor to construct a new GetRequestURL.
     *
     * @param url Base url (no '?')
     */
    public ParameterizedRequestURL(String url) {
        this.params = new ArrayList<>();
        this.url = new StringBuilder(url);
    }

    /**
     * Constructor to construct a new GetRequestURL.
     *
     * @param params ArrayList of params
     * @param url    Base url (no '?')
     */
    public ParameterizedRequestURL(String url, ArrayList<RequestParam> params) {
        this.params = params;
        this.url = new StringBuilder(url);
    }

    /**
     * Adds the given path variable to the url
     *
     * @param variable Variable to add
     * @return this
     */
    public ParameterizedRequestURL addPathVar(String variable) {
        if (variable == null || url == null) return this;
        try {
            if (url.charAt(url.length() - 1) != '/') url.append('/');
            url.append(URLEncoder.encode(variable, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * Adds the given path variable to the url
     *
     * @param variable Variable to add
     * @return this
     */
    public ParameterizedRequestURL addPathVar(int variable) {
        if (url != null) {
            if (url.charAt(url.length() - 1) != '/') url.append('/');
            url.append(variable);
        }
        return this;
    }

    /**
     * Adds a given parameter to the list of parameters for this url
     *
     * @param param parameter to add
     * @return this (allows stacking calls)
     */
    public ParameterizedRequestURL addParam(RequestParam param) {
        if (param == null) return this;
        params.add(param);
        return this;
    }

    /**
     * Adds a parameter
     *
     * @param name  Name of the parameter
     * @param value Value of the parameter
     * @return this (allows stacking calls)
     */
    public ParameterizedRequestURL addParam(String name, Object value) {
        if (value == null) return this;
        params.add(new RequestParam(name, value));
        return this;
    }

    /**
     * Adds an array of parameters
     *
     * @param params Array to add
     * @return this (allows stacking calls)
     */
    public ParameterizedRequestURL addArray(RequestParam[] params) {
        if (params == null) return this;
        this.params.addAll(Arrays.asList(params));
        return this;
    }

    /**
     * Adds a specific item from an array of parameters
     *
     * @param params Array to get param from
     * @param index  index of param to add (-1 = all)
     * @return this (allows stacking calls)
     */
    public ParameterizedRequestURL addArray(RequestParam[] params, int index) {
        if (params == null) return this;
        if (index == -1) {
            this.params.addAll(Arrays.asList(params));
        } else {
            this.params.add(params[index]);
        }

        return this;
    }

    /**
     * Adds an array of parameters
     *
     * @param name   Name of the parameter
     * @param values Values for the parameter
     * @return this (allows stacking calls)
     */
    public ParameterizedRequestURL addArray(String name, Object[] values) {
        if (values == null) return this;
        StringBuilder builder = new StringBuilder();
        for (Object value : values) {
            builder.append(value).append(",");
        }
        params.add(new RequestParam(name, builder.substring(0, builder.length() - 1)));
        return this;
    }

    /**
     * Adds an array of parameters
     *
     * @param name   Name of the parameter
     * @param values Values for the parameter
     * @param index  Index of the array to add (-1 = all)
     * @return this (allows stacking calls)
     */
    public ParameterizedRequestURL addArray(String name, Object[] values, int index) {
        if (values == null) return this;
        if (index == -1) {
            StringBuilder builder = new StringBuilder();
            for (Object value : values) {
                builder.append(value).append(",");
            }
            params.add(new RequestParam(name, builder.substring(0, builder.length() - 1)));
        } else {
            params.add(new RequestParam(name, values[index]));
        }
        return this;
    }

    /**
     * Turns the GetRequestURL into an encoded url with all of the parameters.
     *
     * @return Proper url with included parameters.
     */
    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (url != null) {
            builder.append(url);
            if (params.size() == 0) {
                return builder.toString();
            }
            builder.append("?");
        }

        for (RequestParam param : params) {
            if (param != null) {
                builder.append(param).append("&");
            }
        }
        return builder.substring(0, builder.length() - 1);
    }

    /**
     * Generates a URI using this ParameterizedRequestURL
     *
     * @return URI for this request url
     */
    public URI toURI() {
        try {
            return new URI(toString());
        } catch (URISyntaxException e) {
            Log.e("URI", "Error converting to uri", e);
            return null;
        }
    }

    /**
     * RequestParam class used to make urls.
     */
    public static class RequestParam {
        /**
         * Name of the parameter
         * (Stuff before the '='
         */
        private final String name;
        /**
         * Value of the parameter.
         * (Stuff after the '=').
         * This object needs a sensible toString method for the mapping to work correctly.
         */
        private final Object value;

        /**
         * Constructor to create a new RequestParam
         *
         * @param name  Name of the parameter (stuff before the '=')
         * @param value Value of the parameter (stuff after the '=') [needs toString method]
         */
        public RequestParam(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        /**
         * Generate a url encoding for this request parameter.
         *
         * @return URL encoded parameter
         */
        @NonNull
        @Override
        public String toString() {
            if (name == null || value == null) return "";
            try {
                return URLEncoder.encode(name, "utf-8") + "=" + URLEncoder.encode(value.toString(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }
    }
}
