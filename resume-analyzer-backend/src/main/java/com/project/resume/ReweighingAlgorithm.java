package com.project.resume;

import java.util.List;
import java.util.function.Function;

public class ReweighingAlgorithm {

    public static void applyReweighing(
            List<Candidate> candidates,
            Function<Candidate, String> groupExtractor,
            String privilegedGroup,
            String unprivilegedGroup) {

        int privSel = 0, privNot = 0;
        int unprivSel = 0, unprivNot = 0;

        for (Candidate c : candidates) {
            String group = groupExtractor.apply(c);

            if (group.equalsIgnoreCase(privilegedGroup)) {
                if (c.isSelected()) privSel++;
                else privNot++;
            } else if (group.equalsIgnoreCase(unprivilegedGroup)) {
                if (c.isSelected()) unprivSel++;
                else unprivNot++;
            }
        }

        int total = privSel + privNot + unprivSel + unprivNot;

        double pPriv = (double) (privSel + privNot) / total;
        double pUnpriv = (double) (unprivSel + unprivNot) / total;
        double pSel = (double) (privSel + unprivSel) / total;
        double pNot = (double) (privNot + unprivNot) / total;

        for (Candidate c : candidates) {
            String group = groupExtractor.apply(c);

            if (group.equalsIgnoreCase(privilegedGroup) && c.isSelected())
                c.setWeight((pPriv * pSel) / privSel);
            else if (group.equalsIgnoreCase(privilegedGroup))
                c.setWeight((pPriv * pNot) / privNot);
            else if (group.equalsIgnoreCase(unprivilegedGroup) && c.isSelected())
                c.setWeight((pUnpriv * pSel) / unprivSel);
            else
                c.setWeight((pUnpriv * pNot) / unprivNot);
        }
    }
}
