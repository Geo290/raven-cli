package com.ravencli.http.factory;

import java.net.URI;
import java.net.http.HttpRequest;

import org.json.JSONObject;

import com.ravencli.http.factory.abstracted.concreteProducts.*;

/**
 * Factory class to create HTTP request based on an identified type
 * (e.g., POST, PUT, GET, DELETE).
 * This class encapsulates the logic for creating different types of HTTP
 */
public class RequestFactory {

    public RequestFactory() {
    }

    /**
     * Creates a request based on the http method, API URI, and request body.
     * 
     * @param requestType: POST, PUT
     * @param uri:         URI
     * @param body:        JSONObject
     * @return PostRquest, PutRequest
     * @throws IllegalArgumentException
     */
    public HttpRequest createRequest(String requestType, URI uri, JSONObject body) {
        if (body == null || body.isEmpty()) {
            throw new IllegalArgumentException("Body cannot be null or empty");
        }
        if (uri == null) {
            throw new IllegalArgumentException("URI cannot be null or empty");
        }

        switch (requestType.toUpperCase()) {
            case "POST":
                return new PostRequest(uri, body).build();
            case "PUT":
                return new PutRequest(uri, body).build();
            default:
                throw new IllegalArgumentException("Invalid request type: " + requestType);
        }
    }

    /**
     * Creates a request based on the http mehtod and API URUI.
     * 
     * @param requestType: GET, DELETE
     * @param uri:         URI
     * @param body:        JSONObject
     * @return PostRquest, PutRequest
     * @throws IllegalArgumentException
     */
    public HttpRequest createRequest(String requestType, URI uri) {
        if (uri == null) {
            throw new IllegalArgumentException("URI cannot be null or empty");
        }
        
        switch (requestType) {
            case "GET":
                return new GetRequest(uri).build();
            case "DELETE":
                return new DeleteRequest(uri).build();
            default:
                throw new IllegalArgumentException("Invalid request type: " + requestType);
        }
    }
}
