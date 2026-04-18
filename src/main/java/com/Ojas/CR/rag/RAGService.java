package com.Ojas.CR.rag;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RAGService {

    // A static set of coding rules simulating a vector database.
    private static final Map<String, String> RULES = Map.of(
            "password", "Avoid hardcoded credentials and secrets.",
            "secret", "Avoid hardcoded credentials and secrets.",
            "key", "Avoid hardcoded credentials and secrets.",
            "==", "Use .equals() for string and object comparison rather than ==.",
            "null", "Validate potential null references before using them to prevent NullPointerExceptions.",
            "System.out", "Avoid using System.out.println. Use a proper logging framework like SLF4J (e.g., log.info).",
            "Exception e",
            "Do not catch generic Exception without proper handling. Log the exact error and do not swallow exceptions.");

    public List<String> retrieveRelevantRules(String code) {
        List<String> relevantRules = new ArrayList<>();
        if (code == null)
            return relevantRules;

        String lowerCode = code.toLowerCase();

        for (Map.Entry<String, String> entry : RULES.entrySet()) {
            if (lowerCode.contains(entry.getKey().toLowerCase())) {
                if (!relevantRules.contains(entry.getValue())) {
                    relevantRules.add(entry.getValue());
                }
            }
        }

        // Always include some baseline quality rules
        relevantRules.add(
                "Follow modern secure Java coding practices (e.g., proper variable visibility, immutability where possible, REST best practices).");
        return relevantRules;
    }
}
