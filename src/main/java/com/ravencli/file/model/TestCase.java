package com.ravencli.file.model;

import java.net.URI;

import org.json.JSONObject;

public class TestCase {
    private URI apiUri;
    private String method;
    private JSONObject requestBody;
    private JSONObject expectedResponse;

    public TestCase(URI apUri, String method, JSONObject requestBody, JSONObject expectedResponse) {
        this.apiUri = apUri;
        this.method = method;
        this.requestBody = requestBody;
        this.expectedResponse = expectedResponse;
    }

    public TestCase(URI apUri,  String method, JSONObject expectedResponse) {
        this.apiUri = apUri;
        this.method = method;
        this.expectedResponse = expectedResponse;
    }

    public URI getApiUri() {
        return this.apiUri;
    }

    public String getRequestMethod() {
        return this.method;
    }

    public JSONObject getRequestBody() {
        return this.requestBody;
    }

    public JSONObject getExpectedResponse() {
        return this.expectedResponse;
    }

}
