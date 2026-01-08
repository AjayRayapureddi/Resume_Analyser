package com.project.resume;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeatureExtractor {

    // List of technical skills to detect
    private static final List<String> SKILLS = Arrays.asList(
            "java", "python", "spring", "sql", "mysql",
            "html", "css", "javascript", "hibernate", "react"
    );

    // -------------------------------
    // 1. Count Technical Skills
    // -------------------------------
    public static int countSkills(String resumeText) {
        int count = 0;
        String lowerText = resumeText.toLowerCase();

        for (String skill : SKILLS) {
            if (lowerText.contains(skill)) {
                count++;
            }
        }
        return count;
    }

    // -------------------------------
    // 2. Detect Education Level
    // -------------------------------
    public static String detectEducation(String resumeText) {
        String text = resumeText.toLowerCase();

        if (text.contains("phd")) {
            return "PhD";
        } else if (text.contains("m.tech") || text.contains("master")) {
            return "Master";
        } else if (text.contains("b.tech") || text.contains("bachelor")
                || text.contains("b.e") || text.contains("be")) {
            return "Bachelor";
        }
        return "Unknown";
    }

    // -------------------------------
    // 3. Detect Years of Experience
    // -------------------------------
    public static int detectExperienceYears(String resumeText) {

        Pattern pattern = Pattern.compile(
                "(\\d+)\\s*(\\+)?\\s*(years|year|yrs|yr)",
                Pattern.CASE_INSENSITIVE
        );

        Matcher matcher = pattern.matcher(resumeText);
        int maxYears = 0;

        while (matcher.find()) {
            int years = Integer.parseInt(matcher.group(1));
            if (years > maxYears) {
                maxYears = years;
            }
        }
        return maxYears;
    }

    // -------------------------------
    // 4. Detect Career Gap
    // -------------------------------
    public static boolean hasCareerGap(String resumeText) {
        String text = resumeText.toLowerCase();

        return text.contains("career gap")
                || text.contains("gap year")
                || text.contains("career break")
                || text.contains("employment gap");
    }
}
