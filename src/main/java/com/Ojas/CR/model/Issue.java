package com.Ojas.CR.model;

import lombok.Data;

@Data
public class Issue {
    private Integer line;
    private String issue;
    private String severity;
    private String fix;
    private String explanation;
}
