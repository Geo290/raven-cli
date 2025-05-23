package com.ravencli.http.factory.abstracted.concreteProducts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.http.HttpRequest;
import java.net.URI;

import org.junit.jupiter.api.Test;

public class GetRequestTest {

    @Test
    void buildGetRequest() {
        URI uri = URI.create("http://example.com");
        GetRequest getRequest = new GetRequest(uri);
        HttpRequest httpRequest = getRequest.build();

        assertEquals("GET", httpRequest.method());
        assertEquals(uri, httpRequest.uri());
        assertEquals(true, httpRequest.headers().firstValue("Content-Type").isPresent());
        assertEquals("application/json", httpRequest.headers().firstValue("Content-Type").get());
    }
}
