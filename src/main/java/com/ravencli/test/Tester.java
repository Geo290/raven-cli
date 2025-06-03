package com.ravencli.test;

import org.json.JSONObject;

public class Tester {
    private JSONObject expectedResponse;
    private JSONObject actualResponse;

    public Tester(JSONObject expectedResponse, JSONObject actualResponse) {
        this.expectedResponse = expectedResponse;
        this.actualResponse = actualResponse;
    }

    public Tester() {

    }

    protected void setExpectedResponse(JSONObject expectedResponse) {
        this.expectedResponse = expectedResponse;
    }

    protected void setActualResponse(JSONObject actualResponse) {
        this.actualResponse = actualResponse;
    }

    public boolean compareExpectedAndActualResponse() {
        return expectedResponse.similar(actualResponse);
    }

    public void handleNullOrEmptyResponses() {
        if (expectedResponse == null || actualResponse == null) {
            throw new IllegalArgumentException("Expected and actual responses cannot be null");
        }
        if (expectedResponse.isEmpty() || actualResponse.isEmpty()) {
            throw new IllegalArgumentException("Expected and actual responses cannot be empty");
        }
    }

    public boolean printResults() {
        final String checkMark = "\u2714";
        final String crossMark = "\u2718";

        System.out.println("==============================");
        System.out.println("Evaluating...");

        for (String key : expectedResponse.keySet()) {
            Object expectedValue = expectedResponse.get(key);
            Object actualValue = actualResponse.opt(key);

            System.out.println("==============================");

            if (expectedValue.equals(actualValue)) {
                System.out.printf("[%s] Test passed %s \n", key, checkMark);
            } else {
                System.out.printf("[%s] Test failed %s \n", key, crossMark);
            }

            System.out.println("==============================");
            System.out.println("Expected: " + expectedValue);
            System.out.println("Actual: " + actualValue);

        }

        return true;

    }

    public boolean test() {
        handleNullOrEmptyResponses();

        boolean comparsionResult = compareExpectedAndActualResponse();

        printResults();

        if (comparsionResult) {
            System.out.println("All tests passed successfully!!!");
        } else {
            System.out.println("Some tests failed. Please check the output above.");
        }

        return true;
    }
}
