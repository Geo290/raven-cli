package com.ravencli.file.handler;

import java.net.URI;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.json.JSONObject;

import com.ravencli.file.model.TestCase;

public class FileHandler {
    protected static JSONObject getJsonObject(File fileUrl) {
        try {
            String content = Files.readString(fileUrl.toPath());
            JSONObject jsonObject = new JSONObject(content);
            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static URI getJsonObjectApiUri(JSONObject object) {
        if (!object.has("api_uri")) {
            throw new IllegalArgumentException("api_uri not found in JSON object");
        }

        if (object.getString("api_uri").isBlank()) {
            throw new IllegalArgumentException("api_uri must not be black or empty");
        }

        return URI.create(object.getString("api_uri"));
    }

    protected static String getJsonObjectRequestMethod(JSONObject object) {
        if (!object.has("request_method")) {
            throw new IllegalArgumentException("request_method not found in JSON object");
        }

        if (object.getString("request_method").isBlank()) {
            throw new IllegalArgumentException("request_method must not be black or empty");
        }

        return object.getString("request_method").toUpperCase();
    }

    protected static JSONObject getJsonObjectRequestBody(JSONObject object) {
        if (!object.has("request_body")) {
            throw new IllegalArgumentException("Request body not found in JSON object");
        }
        return object.getJSONObject("request_body");

    }

    protected static JSONObject getJsonObjectExpectedResponse(JSONObject object) {
        if (!object.has("expected_response")) {
            throw new IllegalArgumentException("expected_response not found in JSON object");
        }

        if (object.getJSONObject("expected_response").isEmpty()) {
            throw new IllegalArgumentException("expected_response must not be black or empty");
        }

        return object.getJSONObject("expected_response");
    }

    public static TestCase parse(File file) throws IOException {
        JSONObject jsonObject = getJsonObject(file);

        URI apUri = getJsonObjectApiUri(jsonObject);
        String requestMethod = getJsonObjectRequestMethod(jsonObject);
        JSONObject requestBody = getJsonObjectRequestBody(jsonObject);
        JSONObject expectedResponse = getJsonObjectExpectedResponse(jsonObject);

        return new TestCase(apUri, requestMethod, requestBody, expectedResponse);
    }
}
