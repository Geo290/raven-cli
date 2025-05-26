package com.ravencli.file.model;

import java.net.URI;

import org.json.JSONObject;

public class TestCase {
    private URI apiUri;
    private String method;
    private JSONObject requestBody;
    private JSONObject expectedResults;

    public TestCase(
        URI apUri, 
        String method, 
        JSONObject requestBody, 
        JSONObject expectedResults
    ) {
        this.apiUri = apUri;
        this.method = method;
        this.requestBody = requestBody;
        this.expectedResults = expectedResults;
    }

    public URI getAPUri() {
        return this.apiUri;
    }

    public String getmethod() {
        return this.method;
    }

    public JSONObject getRequestBody() {
        return this.requestBody;
    }

    public JSONObject getExpectedResults() {
        return this.expectedResults;
    }

}
