package com.ravencli.controller;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

import com.ravencli.file.handler.FileHandler;
import com.ravencli.file.model.TestCase;
import com.ravencli.http.client.RestClient;
import com.ravencli.test.Tester;

public class Controller {
    private static RestClient restClient = new RestClient();
    private static Tester tester = new Tester();
    static TestCase testCase;

    public static File readFile(String filePath) {
        File testCaseFile = new File("raven/tests", filePath);

        if (!testCaseFile.exists()) {
            System.out.println("ERROR: TEST CASE NOT FOUND");
            System.out.println("Please ensure the test cases exist within the raven/tests directory");
            return null;
        }

        return testCaseFile;
    }

    public static TestCase buildTestCase(File file) {
        try {
            testCase = FileHandler.parse(file);
            return testCase;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static HttpRequest buildHttpRequest(TestCase testCase) {
        if (testCase == null) {
            throw new IllegalArgumentException("Test case cannot be null");
        }

        if (testCase.getRequestBody() == null || testCase.getRequestBody().isEmpty()) {
            return restClient.buildRequest(
                    testCase.getRequestMethod(),
                    testCase.getApiUri());
        }

        return restClient.buildRequest(
                testCase.getRequestMethod(),
                testCase.getApiUri(),
                testCase.getRequestBody());
    }

    public static HttpResponse<String> executeRequest(HttpRequest request) {
        try {
            HttpResponse<String> response = restClient.execute(request);
            if (response == null) {
                throw new RuntimeException("Failed to execute request. Response is null");
            }
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void runTestCase(TestCase testCase, HttpResponse<String> response) {
        final JSONObject rawExpectedResponse = testCase.getExpectedResponse();
        final JSONObject expectedResponse = rawExpectedResponse.getJSONObject("body");
        final JSONObject actualResponse = new JSONObject(response.body());
        
        System.out.println(expectedResponse);
        System.out.println("=================================");
        System.out.println(actualResponse);

        tester.setExpectedResponse(expectedResponse);
        tester.setActualResponse(actualResponse);
        tester.test();
    }

}
