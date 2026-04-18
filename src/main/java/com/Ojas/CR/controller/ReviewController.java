package com.Ojas.CR.controller;

import com.Ojas.CR.model.ReviewRequest;
import com.Ojas.CR.model.ReviewResponse;
import com.Ojas.CR.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // Vite default port
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/review")
    public ResponseEntity<ReviewResponse> reviewCode(@RequestBody ReviewRequest request) {
        if (request == null || request.getCode() == null || request.getCode().trim().isEmpty()) {
            ReviewResponse errorResponse = new ReviewResponse();
            errorResponse.setSummary("Code cannot be empty.");
            errorResponse.setBugs(new ArrayList<>());
            errorResponse.setSecurity(new ArrayList<>());
            errorResponse.setPerformance(new ArrayList<>());
            errorResponse.setQuality(new ArrayList<>());
            return ResponseEntity.badRequest().body(errorResponse);
        }

        ReviewResponse response = reviewService.analyzeCode(request);
        return ResponseEntity.ok(response);
    }
}
