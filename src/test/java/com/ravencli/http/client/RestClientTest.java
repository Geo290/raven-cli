package com.ravencli.http.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;


import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RestClientTest {
    private RestClient client = new RestClient();
    private URI uri = URI.create("https://jsonplaceholder.typicode.com/users/1");
    private JSONObject body = new JSONObject().put("key", "value");

    HttpRequest post = client.buildRequest("POST", uri, body);
    HttpRequest put = client.buildRequest("PUT", uri, body);
    HttpRequest delete = client.buildRequest("DELETE", uri);
    HttpRequest get = client.buildRequest("GET", uri);

    @Test
    void validateRequestMethods() {
        assertEquals("POST", post.method());
        assertEquals("GET", get.method());
        assertEquals("PUT", put.method());
        assertEquals("DELETE", delete.method());
    }

    @Test
    void checkRequestUri() {
        assertEquals(uri, post.uri());
        assertEquals(uri, get.uri());
        assertEquals(uri, put.uri());
        assertEquals(uri, delete.uri());
    }

    @Test
    void checkRequestBody() {
        assertTrue(post.bodyPublisher().isPresent());
        assertTrue(put.bodyPublisher().isPresent());
        assertFalse(get.bodyPublisher().isPresent());
        assertFalse(delete.bodyPublisher().isPresent());
    }

    @Test
    void checkRequestHeaders() {
        assertEquals("application/json", post.headers().firstValue("Content-Type").orElse(""));
        assertEquals("application/json", put.headers().firstValue("Content-Type").orElse(""));
        assertEquals("application/json", get.headers().firstValue("Content-Type").orElse(""));
        assertEquals("application/json", delete.headers().firstValue("Content-Type").orElse(""));
    }

    @Test
    void testGetRequestExecution() {
        HttpResponse<String> response = client.execute(get);
        JSONObject responseBody = new JSONObject(response.body());
        assertEquals(200, response.statusCode());
        assertEquals(!responseBody.isEmpty(), true, "Response body should not be empty");
        System.out.println("Response :");
        System.out.println(responseBody);
    }
}
