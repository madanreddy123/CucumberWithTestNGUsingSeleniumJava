package com.utility;

import java.io.IOException;
import java.util.Base64;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ZephyrIntegration {

    private final String baseUrl;
    private final String username;
    private final String apiToken;
    private final String auth;
    private String cycleId;

    public ZephyrIntegration(String baseUrl, String username, String apiToken) {
        this.baseUrl = baseUrl;
        this.username = username;
        this.apiToken = apiToken;
        this.auth = Base64.getEncoder().encodeToString((username + ":" + apiToken).getBytes());
    }

    public String getProjectId(String projectName) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(baseUrl + "rest/api/2/project");
        request.setHeader("Authorization", "Basic " + auth);
        request.setHeader("Content-Type", "application/json");

        CloseableHttpResponse response = httpClient.execute(request);
        String responseString = EntityUtils.toString(response.getEntity());

        System.out.println("Response String: " + responseString); // Print response for debugging

        if (responseString != null && !responseString.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(responseString);

            for (JsonNode project : jsonResponse) {
                if (project.get("name").asText().equalsIgnoreCase(projectName)) {
                    return project.get("id").asText();
                }
            }

            throw new IOException("Project ID not found for project name: " + projectName);
        } else {
            throw new IOException("Empty or null response from API");
        }
    }

    public String createTestCycle(String projectId, String cycleName) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(baseUrl + "rest/zapi/latest/cycle");
        request.setHeader("Authorization", "Basic " + auth);
        request.setHeader("Content-Type", "application/json");

        // Example JSON payload for creating a test cycle
        String jsonPayload = String.format("{\"name\": \"%s\", \"projectId\": \"%s\"}", cycleName, projectId);
        StringEntity params = new StringEntity(jsonPayload);
        request.setEntity(params);

        System.out.println("Creating Zephyr Test Cycle: " + cycleName);
        CloseableHttpResponse response = httpClient.execute(request);
        String responseString = EntityUtils.toString(response.getEntity());

        System.out.println("Response String: " + responseString); // Print response for debugging

        if (response.getStatusLine().getStatusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(responseString);
            this.cycleId = jsonResponse.get("id").asText();
            System.out.println("Created Test Cycle ID: " + cycleId);
            return cycleId;
        } else {
            throw new IOException("Failed to create test cycle. Status code: " + response.getStatusLine().getStatusCode());
        }
    }


    public void updateZephyrExecutionStatus(String testCaseKey, String status) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(baseUrl);
        request.setHeader("Authorization", "Basic " + auth);
        request.setHeader("Content-Type", "application/json");

        String jsonPayload = String.format("{\"status\": \"%s\", \"cycleId\": \"%s\", \"testCaseKey\": \"%s\"}", status, cycleId, testCaseKey);
        StringEntity params = new StringEntity(jsonPayload);
        request.setEntity(params);

        System.out.println("Updating Zephyr for Test Case: " + testCaseKey + " with Status: " + status);
        CloseableHttpResponse response = httpClient.execute(request);
        String responseString = EntityUtils.toString(response.getEntity());

        System.out.println("Zephyr API Response: " + responseString); // Print response for debugging
    }
}
