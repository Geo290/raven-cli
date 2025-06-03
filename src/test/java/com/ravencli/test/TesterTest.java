package com.ravencli.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TesterTest {
    private Tester tester = new Tester();
    private JSONObject expectedResponse = new JSONObject()
            .put("name", "John Doe")
            .put("age", 30)
            .put("city", "New York");

    private JSONObject emptyResponse = new JSONObject();

    private JSONObject actualResponse1 = new JSONObject()
            .put("name", "John Doe")
            .put("age", 30)
            .put("city", "New York");

    private JSONObject actualResponse2 = new JSONObject()
            .put("name", "Smith Diane")
            .put("age", 25)
            .put("city", "New York");

    private JSONObject actualResponse3 = new JSONObject()
            .put("NAME", "Jake Res")
            .put("city", "Minneapolis");

    @BeforeEach
    public void setUp() {
        tester.setExpectedResponse(expectedResponse);
    }

    @Test
    public void testCompareExpectedAndActualResponse() {
        tester.setActualResponse(actualResponse1);
        assertTrue(tester.compareExpectedAndActualResponse(), "Responses should be similar");

        tester.setActualResponse(actualResponse2);
        assertTrue(!tester.compareExpectedAndActualResponse(), "Responses should not be similar");

        tester.setActualResponse(actualResponse3);
        assertTrue(!tester.compareExpectedAndActualResponse(), "Responses should not be similar");
    }

    @Test
    public void testHandleNullOrEmptyResponses() {
        tester.setActualResponse(emptyResponse);
        assertThrows(IllegalArgumentException.class, () -> tester.handleNullOrEmptyResponses());
    }

    @Test
    public void testPrintResults() {
        tester.setActualResponse(actualResponse1);
        assertTrue(tester.printResults(), "Rsults should be printed successfully");

        tester.setActualResponse(actualResponse2);
        assertTrue(tester.printResults(), "Rsults should be printed successfully");

        tester.setActualResponse(actualResponse3);
        assertTrue(tester.printResults(), "Rsults should be printed successfully");
    }

    @Test
    public void testMainTestinFunction() {
        tester.setActualResponse(actualResponse1);
        assertTrue(tester.test(), "Rsults should be printed successfully");

        tester.setActualResponse(actualResponse3);
        assertTrue(tester.test(), "Rsults should be printed successfully");
    }
}
