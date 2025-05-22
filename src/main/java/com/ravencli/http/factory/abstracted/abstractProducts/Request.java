package com.ravencli.http.factory.abstracted.abstractProducts;

import java.net.URI;
import java.net.http.HttpRequest;

import org.json.JSONObject;

public abstract class Request {
    protected final URI uri;
    protected final JSONObject body;
    protected final HttpRequest.Builder requestBuilder;

    
    public Request(URI uri, JSONObject body) {
        this.uri = uri;
        this.body = body; 
        this.requestBuilder = HttpRequest.newBuilder();  
    }
    
    public Request(URI uri) {
        this(uri, null);
    }
    
    public abstract HttpRequest build();
    
}
