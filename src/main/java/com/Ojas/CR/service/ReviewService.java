package com.Ojas.CR.service;

import com.Ojas.CR.llm.OpenAIService;
import com.Ojas.CR.llm.PromptBuilder;
import com.Ojas.CR.model.ReviewRequest;
import com.Ojas.CR.model.ReviewResponse;
import com.Ojas.CR.rag.RAGService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class ReviewService {

    private final RAGService ragService;
    private final PromptBuilder promptBuilder;
    private final OpenAIService openAIService;
    private final ObjectMapper objectMapper;

    public ReviewService(RAGService ragService, PromptBuilder promptBuilder, OpenAIService openAIService,
            ObjectMapper objectMapper) {
        this.ragService = ragService;
        this.promptBuilder = promptBuilder;
        this.openAIService = openAIService;
        this.objectMapper = objectMapper;
    }

    public ReviewResponse analyzeCode(ReviewRequest request) {
        try {
            // 1. Retrieve RAG rules
            List<String> rules = ragService.retrieveRelevantRules(request.getCode());

            // 2. Build Prompt
            String prompt = promptBuilder.buildPrompt(request.getCode(), rules);

            // 3. Get LLM response (raw JSON string)
            String jsonResponse = openAIService.getReview(prompt);

            // 4. Parse JSON
            ReviewResponse response = objectMapper.readValue(jsonResponse, ReviewResponse.class);

            // Ensure lists are not null
            if (response.getBugs() == null)
                response.setBugs(new ArrayList<>());
            if (response.getSecurity() == null)
                response.setSecurity(new ArrayList<>());
            if (response.getPerformance() == null)
                response.setPerformance(new ArrayList<>());
            if (response.getQuality() == null)
                response.setQuality(new ArrayList<>());

            return response;
        } catch (Exception e) {
            ReviewResponse errorResponse = new ReviewResponse();
            errorResponse.setSummary("Failed to analyze code: " + e.getMessage());
            errorResponse.setBugs(new ArrayList<>());
            errorResponse.setSecurity(new ArrayList<>());
            errorResponse.setPerformance(new ArrayList<>());
            errorResponse.setQuality(new ArrayList<>());
            return errorResponse;
        }
    }
}
