package com.ravencli.file.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ravencli.file.model.TestCase;

public class FileHandlerTest {
    private FileHandler handler;
    private JSONObject jsonObject;

    @BeforeEach
    void setUp() throws MalformedURLException {
        final Path TEST_FILES_ROOT_DIR = Paths.get("src", "test", "resources", "jsonTestCaseFiles");
        final Path testFile1 = TEST_FILES_ROOT_DIR.resolve("testCase1.json");
        System.out.println("Test File Path: " + testFile1.toAbsolutePath());
        handler = new FileHandler(testFile1);
        jsonObject = handler.getJsonObject();
    }

    @Test
    void testJsonObjectParsing() {
        final URI uri = handler.getJsonObjectUri(jsonObject);
        final String method = handler.getJsonObjectMethod(jsonObject);
        final JSONObject body = handler.getJsonObjectRequestBody(jsonObject);
        final JSONObject response = handler.getJsonObjectExpectedResponse(jsonObject);

        TestCase testCase = new TestCase(uri, method, body, response);

        assertTrue(testCase != null, "TestCase should not be null");
    }

    @Test
    void testGetJsonObjectUri() {
        final URI expectedUri = URI.create("https://api.example.com");
        final URI actualUri = handler.getJsonObjectUri(jsonObject);

        assertEquals(expectedUri, actualUri);
    }

    @Test
    void testGetJsonObjectMethod() {
        final String actualMethod = handler.getJsonObjectMethod(jsonObject);

        assertEquals("POST", actualMethod);
    }

    @Test
    void testGetJsonObjectRequestBody() {
        final JSONObject expectedRequestBody = new JSONObject().put("key", "value");
        final JSONObject actualRequestBody = handler.getJsonObjectRequestBody(jsonObject);

        assertEquals(expectedRequestBody.toString(), actualRequestBody.toString());

    }

    @Test
    void testGetJsonObjectExpectedResponse() {
        JSONObject expectedResponse = new JSONObject();
        expectedResponse.put("status", 200);
        expectedResponse.put("data", new JSONObject());

        final JSONObject actualResponse = handler.getJsonObjectExpectedResponse(jsonObject);

        assertEquals(expectedResponse.toString(), actualResponse.toString());
    }

}
