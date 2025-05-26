package com.ravencli.file.handler;

import java.net.URI;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.JSONObject;

import com.ravencli.file.model.TestCase;

public class FileHandler {
    private Path fileUrl;

    public FileHandler(Path fileUrl) {
        this.fileUrl = fileUrl;
    }

    protected JSONObject getJsonObject() {
        try {
            String content = Files.readString(fileUrl);
            JSONObject jsonObject = new JSONObject(content);
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected URI getJsonObjectUri(JSONObject object) {
        if (!object.has("API_URI")) {
            throw new IllegalArgumentException("API_URI not found in JSON object");
        }

        if (object.getString("API_URI").isBlank()) {
            throw new IllegalArgumentException("API_URI must not be black or empty");
        }

        return URI.create(object.getString("API_URI"));
    }

    protected String getJsonObjectMethod(JSONObject object) {
        if (!object.has("METHOD")) {
            throw new IllegalArgumentException("METHOD not found in JSON object");
        }

        if (object.getString("METHOD").isBlank()) {
            throw new IllegalArgumentException("METHOD must not be black or empty");
        }

        return object.getString("METHOD").toUpperCase();
    }

    protected JSONObject getJsonObjectRequestBody(JSONObject object) {
        if (!object.has("REQUEST_BODY")) {
            throw new IllegalArgumentException("REQUEST_BODY not found in JSON object");
        }

        if (object.getJSONObject("REQUEST_BODY").isEmpty()) {
            throw new IllegalArgumentException("REQUEST_BODY must not be black or empty");
        }
        return object.getJSONObject("REQUEST_BODY");

    }

    protected JSONObject getJsonObjectExpectedResponse(JSONObject object) {
        if (!object.has("EXPECTED_RESULTS")) {
            throw new IllegalArgumentException("EXPECTED_RESULTS not found in JSON object");
        }

        if (object.getJSONObject("EXPECTED_RESULTS").isEmpty()) {
            throw new IllegalArgumentException("EXPECTED_RESULTS must not be black or empty");
        }

        return object.getJSONObject("EXPECTED_RESULTS");
    }

    public TestCase parse() throws IOException {
        JSONObject jsonObject = getJsonObject();

        URI apUri = getJsonObjectUri(jsonObject);
        String method = getJsonObjectMethod(jsonObject);
        JSONObject requestBody = getJsonObjectRequestBody(jsonObject);
        JSONObject expectedResponse = getJsonObjectExpectedResponse(jsonObject);

        return new TestCase(apUri, method, requestBody, expectedResponse);
    }
}
