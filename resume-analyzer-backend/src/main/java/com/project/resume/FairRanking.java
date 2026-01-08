package com.project.resume;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FairRanking {

    public static void rankCandidates(List<Candidate> candidates) {

        Collections.sort(candidates, new Comparator<Candidate>() {
            @Override
            public int compare(Candidate c1, Candidate c2) {
                return Double.compare(c2.getWeight(), c1.getWeight());
            }
        });
    }
}
