package com.ravencli.http.factory.abstracted.concreteProducts;

import java.net.URI;
import java.net.http.HttpRequest;

import com.ravencli.http.factory.abstracted.abstractProducts.Request;

public class GetRequest extends Request {
    public GetRequest(URI uri) {
        super(uri, null);
    }

    @Override
    public HttpRequest build() {
        return requestBuilder
            .uri(uri)
            .headers("Content-Type", "application/json")
            .GET()
            .build();
    }
}
