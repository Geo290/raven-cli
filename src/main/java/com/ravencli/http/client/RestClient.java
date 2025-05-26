package com.ravencli.http.client;

import java.io.IOException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import com.ravencli.http.factory.RequestFactory;

public class RestClient {
    private HttpClient client = HttpClient.newHttpClient();
    private RequestFactory factory;
    
    public RestClient() {
        this.factory = new RequestFactory();
    }

    public HttpRequest buildRequest(String requestType, URI uri, JSONObject body) {
        return factory.createRequest(requestType, uri, body);
    }

    public HttpRequest buildRequest(String requestType, URI uri) {
        return factory.createRequest(requestType, uri);
    }

    public void execute(HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) { // Create a timeout exception
            e.printStackTrace();
        }
    }
}
