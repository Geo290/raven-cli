package com.ravencli.http.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

import com.ravencli.file.handler.FileHandler;
import com.ravencli.file.model.TestCase;

import java.net.MalformedURLException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

import java.io.IOException;
import java.io.File;

public class RestClientTest {
    private RestClient client = new RestClient();

    TestCase testCasePost, testCaseGet, testCasePut, testCaseDelete;
    HttpRequest post, put, delete, get;

    @BeforeEach
    void setUp() throws MalformedURLException, NullPointerException, IOException {
        try {
            testCasePost = FileHandler.parse(new File("raven/tests", "testCasePost.json"));
            testCasePut = FileHandler.parse(new File("raven/tests", "testCasePut.json"));
            testCaseDelete = FileHandler.parse(new File("raven/tests", "testCaseDelete.json"));
            testCaseGet = FileHandler.parse(new File("raven/tests", "testCaseGet.json"));

            post = client.buildRequest(
                    testCasePost.getRequestMethod(),
                    testCasePost.getApiUri(),
                    testCasePost.getRequestBody());
            put = client.buildRequest(
                    testCasePut.getRequestMethod(),
                    testCasePut.getApiUri(),
                    testCasePut.getRequestBody());
            delete = client.buildRequest(
                    testCaseDelete.getRequestMethod(),
                    testCaseDelete.getApiUri());
            get = client.buildRequest(
                    testCaseGet.getRequestMethod(),
                    testCaseGet.getApiUri());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void validateRequestMethods() {
        assertEquals("POST", post.method());
        assertEquals("PUT", put.method());
        assertEquals("DELETE", delete.method());
        assertEquals("GET", get.method());
    }

    @Test
    void checkRequestUri() {
        URI postUri = URI.create("http://localhost:8080/teachers/save");
        URI getUri = URI.create("http://localhost:8080/teachers/find-by-id/1");
        URI putUri = URI.create("http://localhost:8080/teachers/update-info/1");
        URI deleteUri = URI.create("http://localhost:8080/teachers/delete/1");

        assertEquals(postUri, post.uri());
        assertEquals(getUri, get.uri());
        assertEquals(putUri, put.uri());
        assertEquals(deleteUri, delete.uri());
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

    // @Test
    // void testGetRequestExecution() {
    //     HttpResponse<String> response = client.execute(get);
    //     JSONObject responseBody = new JSONObject(response.body());

    //     assertEquals(200, response.statusCode());
    //     assertEquals(!responseBody.isEmpty(), true, "Response body should not be empty");

    //     System.out.println("Response :");
    //     System.out.println(responseBody);
    // }

    // @Test
    // void testPostRequestExecution() {
    //     HttpResponse<String> response = client.execute(post);
    //     JSONObject responseBody = new JSONObject(response.body());

    //     assertEquals(200, response.statusCode());
    //     assertEquals(!responseBody.isEmpty(), true, "Response body should not be empty");

    //     System.out.println("Response :");
    //     System.out.println(responseBody);
    // }

    // @Test
    // void testPutRequestExecution() {
    //     HttpResponse<String> response = client.execute(put);
    //     JSONObject responseBody = new JSONObject(response.body());

    //     assertEquals(200, response.statusCode());
    //     assertEquals(!responseBody.isEmpty(), true, "Response body should not be empty");

    //     System.out.println("Response :");
    //     System.out.println(responseBody);
    // }

    // @Test
    // void testDeleteRequestExecution() {
    //     HttpResponse<String> response = client.execute(delete);
    //     JSONObject responseBody = new JSONObject(response.body());

    //     assertEquals(200, response.statusCode());
    //     assertEquals(!responseBody.isEmpty(), true, "Response body should not be empty");

    //     System.out.println("Response :");
    //     System.out.println(responseBody);
    // }
}
