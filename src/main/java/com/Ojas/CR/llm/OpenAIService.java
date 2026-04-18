package com.Ojas.CR.llm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Service
public class OpenAIService {

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.base-url:https://api.openai.com/v1}")
    private String baseUrl;

    @Value("${spring.ai.openai.chat.options.model:llama3-70b-8192}")
    private String modelName;

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public OpenAIService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getReview(String promptText) {
        try {
            // Standardizing the base URL so we don't duplicate paths
            String url = baseUrl;
            if (!url.endsWith("/v1")) {
                url += "/v1";
            }
            
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("model", modelName);
            
            ArrayNode messages = requestBody.putArray("messages");
            ObjectNode message = objectMapper.createObjectNode();
            message.put("role", "user");
            message.put("content", promptText);
            messages.add(message);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url + "/chat/completions"))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() != 200) {
               System.err.println("Groq HTTP Error: " + response.body());
               return "{\"summary\":\"Groq API Error: " + response.statusCode() + " - Please check server logs for details.\"}";
            }

            JsonNode root = objectMapper.readTree(response.body());
            String textResponse = root.path("choices").get(0).path("message").path("content").asText();

            if (textResponse != null) {
                textResponse = textResponse.trim();
                if (textResponse.startsWith("```json")) {
                    textResponse = textResponse.substring(7);
                } else if (textResponse.startsWith("```")) {
                    textResponse = textResponse.substring(3);
                }
                if (textResponse.endsWith("```")) {
                    textResponse = textResponse.substring(0, textResponse.length() - 3);
                }
                return textResponse.trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"summary\":\"Failed to analyze code: " + e.getMessage() + "\"}";
        }
        return "{}";
    }
}
