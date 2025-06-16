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

    /**
     * 
     * @param String requestMethod
     * @param URI uri
     * @param JSONObject body
     * @return HttpRequest
     */
    public HttpRequest buildRequest(String requestMethod, URI uri, JSONObject body) {
        return factory.createRequest(requestMethod, uri, body);
    }

    /**
     * 
     * @param String requestMethod
     * @param URI uri
     * @param JSONObject expectedResponse
     * @return HttpRequest
     */
    public HttpRequest buildRequest(String requestMethod, URI uri) {
        return factory.createRequest(requestMethod, uri);
    }

    public HttpResponse<String> execute(HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response;

        } catch (IOException | InterruptedException e) { // Create a timeout exception
            e.printStackTrace();
        }

        return null;
    }
}
