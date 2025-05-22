package com.ravencli.http.factory.abstracted.concreteProducts;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;

import org.json.JSONObject;

import com.ravencli.http.factory.abstracted.abstractProducts.Request;

public class PostRequest extends Request {
    public PostRequest(URI uri, JSONObject body) {
        super(uri, body);
    }

    public PostRequest(URI uri) {
        super(uri);
    }

    @Override
    public HttpRequest build() {
        BodyPublisher requestBody = HttpRequest.BodyPublishers.ofString(body.toString());
        return requestBuilder
            .uri(uri)
            .headers("Content-Type", "application/json")
            .POST(requestBody)
            .build();
    }

}
