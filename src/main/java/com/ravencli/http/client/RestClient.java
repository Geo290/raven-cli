package com.ravencli.http.client;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.ravencli.http.factory.RequestFactory;

public class RestClient {
    private HttpClient client = HttpClient.newHttpClient();
    private RequestFactory factory;
    public RestClient() {
        this.factory = new RequestFactory();
    }

    public HttpRequest buildRequest(String requestType) {
        HttpRequest request = factory.createRequest(requestType, null);
        return request;
    }

    public void execute(HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) { // Create a timeout exception
            e.printStackTrace();
        }
    }
}
