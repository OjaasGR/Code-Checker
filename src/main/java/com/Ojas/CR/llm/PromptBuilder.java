package com.Ojas.CR.llm;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromptBuilder {

    public String buildPrompt(String code, List<String> rules) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("You are a senior full-stack engineer and strict code reviewer.\n");
        prompt.append(
                "Perform a FIRST-PASS professional code review on the below code, taking into account the following specific rules Context.\n\n");

        prompt.append("### CONTEXT (RULES to strictly follow):\n");
        if (rules != null && !rules.isEmpty()) {
            for (String rule : rules) {
                prompt.append("- ").append(rule).append("\n");
            }
        } else {
            prompt.append("None specific.\n");
        }

        prompt.append("\n### INPUT CODE:\n");
        prompt.append("```\n").append(code).append("\n```\n\n");

        prompt.append("### OUTPUT FORMAT (STRICT JSON)\n");
        prompt.append("Return ONLY valid JSON. No markdown, no explanations outside JSON.\n");
        prompt.append("{\n");
        prompt.append("  \"summary\": \"1-2 line overview\",\n");
        prompt.append(
                "  \"bugs\": [{ \"line\": 1, \"issue\": \"\", \"severity\": \"HIGH|MEDIUM|LOW\", \"fix\": \"\", \"explanation\": \"\" }],\n");
        prompt.append(
                "  \"security\": [{ \"line\": 1, \"issue\": \"\", \"severity\": \"HIGH|MEDIUM|LOW\", \"fix\": \"\", \"explanation\": \"\" }],\n");
        prompt.append(
                "  \"performance\": [{ \"line\": 1, \"issue\": \"\", \"severity\": \"HIGH|MEDIUM|LOW\", \"fix\": \"\", \"explanation\": \"\" }],\n");
        prompt.append(
                "  \"quality\": [{ \"line\": 1, \"issue\": \"\", \"severity\": \"HIGH|MEDIUM|LOW\", \"fix\": \"\", \"explanation\": \"\" }]\n");
        prompt.append("}\n");
        prompt.append("Return empty arrays [] if no issues are found. Do NOT hallucinate issues.\n");

        return prompt.toString();
    }
}
