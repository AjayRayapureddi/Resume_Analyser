package com.project.resume;

import java.util.List;
import java.util.function.Function;

public class BiasDetector {

    // Generic SPD calculator
    public static double calculateSPD(
            List<Candidate> candidates,
            Function<Candidate, String> groupExtractor,
            String privilegedGroup,
            String unprivilegedGroup) {

        int privSelected = 0, privTotal = 0;
        int unprivSelected = 0, unprivTotal = 0;

        for (Candidate c : candidates) {
            String group = groupExtractor.apply(c);

            if (group.equalsIgnoreCase(privilegedGroup)) {
                privTotal++;
                if (c.isSelected()) privSelected++;
            } else if (group.equalsIgnoreCase(unprivilegedGroup)) {
                unprivTotal++;
                if (c.isSelected()) unprivSelected++;
            }
        }

        double privRate = privTotal == 0 ? 0 : (double) privSelected / privTotal;
        double unprivRate = unprivTotal == 0 ? 0 : (double) unprivSelected / unprivTotal;

        return unprivRate - privRate;
    }

    // Generic DI calculator
    public static double calculateDI(
            List<Candidate> candidates,
            Function<Candidate, String> groupExtractor,
            String privilegedGroup,
            String unprivilegedGroup) {

        int privSelected = 0, privTotal = 0;
        int unprivSelected = 0, unprivTotal = 0;

        for (Candidate c : candidates) {
            String group = groupExtractor.apply(c);

            if (group.equalsIgnoreCase(privilegedGroup)) {
                privTotal++;
                if (c.isSelected()) privSelected++;
            } else if (group.equalsIgnoreCase(unprivilegedGroup)) {
                unprivTotal++;
                if (c.isSelected()) unprivSelected++;
            }
        }

        double privRate = privTotal == 0 ? 0 : (double) privSelected / privTotal;
        double unprivRate = unprivTotal == 0 ? 0 : (double) unprivSelected / unprivTotal;

        return privRate == 0 ? 0 : unprivRate / privRate;
    }
}
