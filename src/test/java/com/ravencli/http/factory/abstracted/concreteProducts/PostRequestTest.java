package com.ravencli.http.factory.abstracted.concreteProducts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.http.HttpRequest;
import java.net.URI;

import org.junit.jupiter.api.Test;  
import org.json.JSONObject;

public class PostRequestTest {

    @Test
    void buildPostRequest() {
        URI uri = URI.create("http://example.com");
        JSONObject body = new JSONObject().put("key","value");
        PostRequest postRequest = new PostRequest(uri, body);
        HttpRequest httpRequest = postRequest.build();

        assertEquals("POST", httpRequest.method());
        assertEquals(uri,httpRequest.uri()); 
        assertEquals(true, httpRequest.headers().firstValue("Content-Type").isPresent());
        assertEquals("application/json", httpRequest.headers().firstValue("Content-Type").get());
    }
}
