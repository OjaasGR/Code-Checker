package com.Ojas.CR.model;

import java.util.List;
import lombok.Data;

@Data
public class ReviewResponse {
    private String summary;
    private List<Issue> bugs;
    private List<Issue> security;
    private List<Issue> performance;
    private List<Issue> quality;
}
