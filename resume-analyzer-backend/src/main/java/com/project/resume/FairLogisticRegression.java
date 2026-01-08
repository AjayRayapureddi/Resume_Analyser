package com.project.resume;

import java.util.List;

public class FairLogisticRegression {

    // Fair scoring (NOT real ML yet – fairness logic simulation)
    public static void applyFairSelection(List<Candidate> candidates) {

        for (Candidate c : candidates) {

            double score = 0.0;

            // Skill importance
            score += c.getSkillCount() * 2;

            // Experience importance
            score += c.getExperienceYears() * 3;

            // Education bonus
            if ("Master".equalsIgnoreCase(c.getEducation())) {
                score += 2;
            } else if ("PhD".equalsIgnoreCase(c.getEducation())) {
                score += 3;
            }

            // Penalty for career gap
            if (c.hasCareerGap()) {
                score -= 1.5;
            }

            // Fairness reweighting
            score *= c.getWeight();

            c.setFairScore(score);
        }
    }
}
