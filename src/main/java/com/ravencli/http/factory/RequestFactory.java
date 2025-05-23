package com.ravencli.http.factory;

import java.net.URI;
import java.net.http.HttpRequest;

import org.json.JSONObject;

import com.ravencli.http.factory.abstracted.concreteProducts.*;

public class RequestFactory {
    private String requestType;

    public RequestFactory(String requestType) {
        this.requestType = requestType;
    }

    public HttpRequest createRequest(URI uri, JSONObject body) {
        String stringifiedType = this.requestType.toString();

        switch (stringifiedType) {
            case "POST":
                return new PostRequest(uri, body).build();
            case "GET":
                return new GetRequest(uri).build();
            case "PUT":
                return new PutRequest(uri, body).build();
            case "DELETE":
                return new DeleteRequest(uri).build();
            default:
                throw new IllegalArgumentException("Invalid request type: " + stringifiedType);
        }
    }
}
