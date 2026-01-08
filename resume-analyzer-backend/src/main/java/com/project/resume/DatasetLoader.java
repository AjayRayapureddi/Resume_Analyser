package com.project.resume;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DatasetLoader {

    public static List<Candidate> loadCandidatesFromCSV(String fileName) {
        List<Candidate> candidates = new ArrayList<>();

        try {
            InputStream is = DatasetLoader.class
                    .getClassLoader()
                    .getResourceAsStream(fileName);

            System.out.println("CSV file requested: " + fileName);
            System.out.println("InputStream is NULL? " + (is == null));

            if (is == null) {
                return candidates;
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            boolean headerSkipped = false;

            while ((line = br.readLine()) != null) {

                // Skip header
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                // Clean line
                line = line.replace("\"", "").trim();
                String[] data = line.split(",");

                if (data.length < 9) continue;

                Candidate c = new Candidate(
                        parseIntSafe(data[0]),              // skillCount
                        parseIntSafe(data[1]),              // experienceYears
                        data[2].trim(),                      // education
                        Boolean.parseBoolean(data[3].trim()),// careerGap
                        data[4].trim(),                      // gender
                        data[5].trim(),                      // collegeTier
                        parseIntSafe(data[6]),               // age
                        data[7].trim(),                      // location
                        Boolean.parseBoolean(data[8].trim()) // selected
                );

                candidates.add(c);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("✅ Total candidates loaded: " + candidates.size());
        return candidates;
    }

    private static int parseIntSafe(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            return 0;
        }
    }
}
