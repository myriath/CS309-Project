package com.example.cs309android.models.gson.request.abstraction;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Multipart request for sending images
 *
 * @author Mitch Hudson, based on code from https://www.maxester.com/blog/2019/10/04/upload-file-image-to-the-server-using-volley-in-android/
 */
public class VolleyMultipartRequest extends Request<NetworkResponse> {
    /**
     * Two hyphens, used for headers
     */
    public static final String TWO_HYPHENS = "--";
    /**
     * CRLF, used for headers
     */
    public static final String LINE_END = "\r\n";
    /**
     * Boundary for the headers
     */
    public static final String BOUNDARY = "apiclient-" + System.currentTimeMillis();

    /**
     * Runs when the request completes successfully
     */
    private final Response.Listener<NetworkResponse> listener;
    /**
     * Runs when the request completes with an error
     */
    private final Response.ErrorListener errorListener;
    /**
     * Header mapping
     */
    private Map<String, String> headers;

    /**
     * Constructor
     *
     * @param method        Request.Method
     * @param url           URL for the request
     * @param listener      Runs when the request completes successfully
     * @param errorListener Runs when the request completes with an error
     */
    public VolleyMultipartRequest(int method, String url, Response.Listener<NetworkResponse> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = listener;
        this.errorListener = errorListener;
    }

    /**
     * Gets the headers
     *
     * @return header mapping
     * @throws AuthFailureError
     */
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    /**
     * Gets the content type
     *
     * @return content type
     */
    @Override
    public String getBodyContentType() {
        return "multipart/form-data;boundary=" + BOUNDARY;
    }

    /**
     * Gets the body
     *
     * @return body bytes
     * @throws AuthFailureError
     */
    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);

        try {
            Map<String, String> params = getParams();
            if (params != null && params.size() > 0) {
                textParse(dos, params, getParamsEncoding());
            }

            Map<String, DataPart> data = getByteData();
            if (data != null && data.size() > 0) {
                dataParse(dos, data);
            }

            dos.writeBytes(TWO_HYPHENS + BOUNDARY + TWO_HYPHENS + LINE_END);

            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Overridden by children
     *
     * @return Map data part label with data byte
     */
    protected Map<String, DataPart> getByteData() {
        return null;
    }

    /**
     * Handles the network response
     *
     * @param response Response from the request
     * @return Either success or error
     */
    @Override
    protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
        try {
            return Response.success(response, HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    /**
     * Delivers the response to the listener
     *
     * @param response Response from the request
     */
    @Override
    protected void deliverResponse(NetworkResponse response) {
        listener.onResponse(response);
    }

    /**
     * Delivers the error to the listener
     *
     * @param error Error from the request
     */
    @Override
    public void deliverError(VolleyError error) {
        errorListener.onErrorResponse(error);
    }

    /**
     * Parse string map into data output stream by key and value
     *
     * @param dos      data output stream
     * @param params   inputs collection
     * @param encoding encode the inputs, default UTF-8
     * @throws IOException
     */
    private void textParse(DataOutputStream dos, Map<String, String> params, String encoding) throws IOException {
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                buildTextPart(dos, entry.getKey(), entry.getValue());
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding not supported: " + encoding, e);
        }
    }

    /**
     * Parses data into the data output stream
     *
     * @param dos  Data output stream
     * @param data Data to parse
     * @throws IOException
     */
    private void dataParse(DataOutputStream dos, Map<String, DataPart> data) throws IOException {
        for (Map.Entry<String, DataPart> entry : data.entrySet()) {
            buildDataPart(dos, entry.getValue(), entry.getKey());
        }
    }

    /**
     * Writes string data into the header and data output stream
     *
     * @param dos   Data output stream
     * @param param name of the input
     * @param value value of the input
     * @throws IOException
     */
    private void buildTextPart(DataOutputStream dos, String param, String value) throws IOException {
        dos.writeBytes(TWO_HYPHENS + BOUNDARY + LINE_END);
        dos.writeBytes("Content-Disposition: form-data; name=\"" + param + "\"" + LINE_END);
        dos.writeBytes(LINE_END);
        dos.writeBytes(value + LINE_END);
    }

    /**
     * Write data into the header and data output stream
     *
     * @param dos  Data output stream
     * @param data Data to write
     * @param name Name of the input
     * @throws IOException
     */
    private void buildDataPart(DataOutputStream dos, DataPart data, String name) throws IOException {
        dos.writeBytes(TWO_HYPHENS + BOUNDARY + LINE_END);
        dos.writeBytes("Content-Disposition: form-data; name=\"" +
                name + "\"; filename=\"" + data.getName() + "\"" + LINE_END);
        if (data.getType() != null && !data.getType().trim().isEmpty()) {
            dos.writeBytes("Content-Type: " + data.getType() + LINE_END);
        }
        dos.writeBytes(LINE_END);

        ByteArrayInputStream in = new ByteArrayInputStream(data.getContent());
        int bytesAvailable = in.available();

        int maxBufferSize = 1024 * 1024;
        int bufferSize = Math.min(bytesAvailable, maxBufferSize);
        byte[] buffer = new byte[bufferSize];

        int bytesRead = in.read(buffer, 0, bufferSize);

        while (bytesRead > 0) {
            dos.write(buffer, 0, bufferSize);
            bytesAvailable = in.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            bytesRead = in.read(buffer, 0, bufferSize);
        }

        dos.writeBytes(LINE_END);
    }

    /**
     * Represents a part of the multipart request data
     */
    static class DataPart {
        /**
         * Name of the file
         */
        private String name;
        /**
         * Content bytes
         */
        private byte[] content;
        /**
         * MIME type
         */
        private String type;

        /**
         * Default constructor
         */
        public DataPart() {
        }

        /**
         * Constructor
         *
         * @param name    name of the data
         * @param content bytes of the content
         */
        DataPart(String name, byte[] content) {
            this.name = name;
            this.content = content;
        }

        /**
         * Getter for the name
         *
         * @return data name
         */
        public String getName() {
            return name;
        }

        /**
         * Getter for the content
         *
         * @return content bytes
         */
        public byte[] getContent() {
            return content;
        }

        /**
         * Getter for the type
         *
         * @return MIME type
         */
        public String getType() {
            return type;
        }
    }
}
