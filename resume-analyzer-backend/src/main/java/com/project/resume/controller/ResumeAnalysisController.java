package com.project.resume.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.resume.BiasDetector;
import com.project.resume.Candidate;
import com.project.resume.DatasetLoader;
import com.project.resume.FairRanking;
import com.project.resume.ReweighingAlgorithm;

@RestController
public class ResumeAnalysisController {

    @GetMapping("/analyze")
    public Map<String, Object> analyzeResumes() {

        Map<String, Object> response = new HashMap<>();

        // STEP 1: Load dataset
        List<Candidate> candidates =
                DatasetLoader.loadCandidatesFromCSV("resumes_dataset.csv");

        // STEP 2: Bias detection (Gender)
        double genderSPD = BiasDetector.calculateSPD(
                candidates, Candidate::getGender, "Male", "Female");

        double genderDI = BiasDetector.calculateDI(
                candidates, Candidate::getGender, "Male", "Female");

        // STEP 3: Apply reweighing
        ReweighingAlgorithm.applyReweighing(
                candidates, Candidate::getGender, "Male", "Female");

        // STEP 4: Fair ranking
        FairRanking.rankCandidates(candidates);

        // STEP 5: Build response
        response.put("genderSPD", genderSPD);
        response.put("genderDI", genderDI);
        response.put("totalCandidates", candidates.size());
        response.put("fairRanking", candidates);

        return response;
    }
}
