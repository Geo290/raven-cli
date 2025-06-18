package com.ravencli.file.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ravencli.file.model.TestCase;

public class FileHandlerTest {
    JSONObject jsonObject, postJO, getJO, putJO, deleteJO;

    @BeforeEach
    void setUp() throws MalformedURLException, NullPointerException, IOException {

        try {
            jsonObject = FileHandler.getJsonObject(new File("raven/tests", "myTestCase.json"));
            deleteJO = FileHandler.getJsonObject(new File("raven/tests", "testCaseDelete.json"));
            postJO = FileHandler.getJsonObject(new File("raven/tests", "testCasePost.json"));
            getJO = FileHandler.getJsonObject(new File("raven/tests", "testCaseGet.json"));
            putJO = FileHandler.getJsonObject(new File("raven/tests", "testCasePut.json"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testJsonObjectParsing() {
        final URI uri = FileHandler.getJsonObjectApiUri(jsonObject);
        final String method = FileHandler.getJsonObjectRequestMethod(jsonObject);
        final JSONObject body = FileHandler.getJsonObjectRequestBody(jsonObject);
        final JSONObject response = FileHandler.getJsonObjectExpectedResponse(jsonObject);

        TestCase testCase = new TestCase(uri, method, body, response);

        assertTrue(testCase != null, "TestCase should not be null");
    }

    @Test
    void testgetJsonObjectApiUri() {
        final URI expectedPostUri = URI.create("http://localhost:8080/teachers/save");
        final URI expectedGetUri = URI.create("http://localhost:8080/teachers/find-by-id/1");
        final URI expectedPutUri = URI.create("http://localhost:8080/teachers/update-info/1");
        final URI expectedDeleteUri = URI.create("http://localhost:8080/teachers/delete/1");

        final URI actualPostUri = FileHandler.getJsonObjectApiUri(postJO);
        final URI actualGetUri = FileHandler.getJsonObjectApiUri(getJO);
        final URI actualPutUri = FileHandler.getJsonObjectApiUri(putJO);
        final URI actualDeleteUri = FileHandler.getJsonObjectApiUri(deleteJO);

        assertEquals(expectedPostUri, actualPostUri);
        assertEquals(expectedGetUri, actualGetUri);
        assertEquals(expectedPutUri, actualPutUri);
        assertEquals(expectedDeleteUri, actualDeleteUri);

    }

    @Test
    void testGetJsonObjectRequestMethod() {
        final String actualPostMethod = FileHandler.getJsonObjectRequestMethod(postJO);
        final String actualGetMethod = FileHandler.getJsonObjectRequestMethod(getJO);
        final String actualPutMethod = FileHandler.getJsonObjectRequestMethod(putJO);
        final String actualDeleteMethod = FileHandler.getJsonObjectRequestMethod(deleteJO);

        assertEquals("POST", actualPostMethod);
        assertEquals("GET", actualGetMethod);
        assertEquals("PUT", actualPutMethod);
        assertEquals("DELETE", actualDeleteMethod);
    }

    @Test
    void testGetJsonObjectRequestBody() {
        final JSONObject expectedRequestBody = new JSONObject().put("key", "value");
        final JSONObject actualRequestBody = FileHandler.getJsonObjectRequestBody(jsonObject);

        assertEquals(expectedRequestBody.toString(), actualRequestBody.toString());

    }

    @Test
    void testGetJsonObjectExpectedResponse() {
        JSONObject expectedResponse = new JSONObject();
        expectedResponse.put("status", 200);
        expectedResponse.put("data", new JSONObject());

        final JSONObject actualResponse = FileHandler.getJsonObjectExpectedResponse(jsonObject);

        assertEquals(expectedResponse.toString(), actualResponse.toString());
    }

}
