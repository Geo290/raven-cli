package com.ravencli.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ravencli.file.model.TestCase;

public class ControllerTest {
    HttpResponse<String> response;
    HttpRequest request;
    TestCase testCase;
    File file;

    @BeforeEach
    void setUp() {
        file = Controller.readFile("testCaseGet.json");
        testCase = Controller.buildTestCase(file);
        request = Controller.buildHttpRequest(testCase);
        response = Controller.executeRequest(request);
    }

    @Test
    public void testReadFile() {
        assertNotNull(file, "File should not be null");
    }

    @Test
    public void testBuildTestCase() {
        System.out.println(file.getAbsolutePath());
        assertNotNull(testCase, "Testcase should not be null");
    }

    @Test
    public void testBuildHttpRquest() {
        System.out.println("Method: " + testCase.getRequestMethod());
        System.out.println("URI: " + testCase.getApiUri());
        System.out.println("Body: " + testCase.getRequestBody());
        assertNotNull(request, "HttpRequest should not be null");
    }

    @Test
    public void testExecuteRequest() {
        System.out.println("Method: " + request.method());
        System.out.println("URI: " + request.uri());
        assertNotNull(response, "HttpResponse should not be null");
        assertEquals(200, response.statusCode(), "Status code should be 200 OK");
    }
}
