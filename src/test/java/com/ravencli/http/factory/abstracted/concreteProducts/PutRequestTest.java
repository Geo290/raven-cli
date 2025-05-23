package com.ravencli.http.factory.abstracted.concreteProducts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.http.HttpRequest;
import java.net.URI;

import org.junit.jupiter.api.Test;
import org.json.JSONObject;

public class PutRequestTest {

    @Test
    void buildPostRequest() {
        URI uri = URI.create("http://example.com");
        JSONObject body = new JSONObject().put("key", "value");
        PutRequest putRequest = new PutRequest(uri, body);
        HttpRequest httpRequest = putRequest.build();

        assertEquals("PUT", httpRequest.method());
        assertEquals(uri, httpRequest.uri());
        assertEquals(true, httpRequest.headers().firstValue("Content-Type").isPresent());
        assertEquals("application/json", httpRequest.headers().firstValue("Content-Type").get());
    }
}
