package com.project.resume;

import java.util.ArrayList;
import java.util.List;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

public class WekaDatasetBuilder {

    public static Instances buildDataset(List<Candidate> candidates) {

        ArrayList<Attribute> attributes = new ArrayList<>();

        attributes.add(new Attribute("skillCount"));
        attributes.add(new Attribute("experienceYears"));

        List<String> educationVals = List.of("Bachelor", "Master", "PhD");
        attributes.add(new Attribute("education", educationVals));

        List<String> careerGapVals = List.of("false", "true");
        attributes.add(new Attribute("careerGap", careerGapVals));

        List<String> classVals = List.of("false", "true");
        attributes.add(new Attribute("selected", classVals));

        Instances data = new Instances("ResumeData", attributes, candidates.size());
        data.setClassIndex(data.numAttributes() - 1);

        for (Candidate c : candidates) {

            DenseInstance instance = new DenseInstance(data.numAttributes());

            instance.setValue(attributes.get(0), c.getSkillCount());
            instance.setValue(attributes.get(1), c.getExperienceYears());
            instance.setValue(attributes.get(2), c.getEducation());
            instance.setValue(attributes.get(3), String.valueOf(c.hasCareerGap()));
            instance.setValue(attributes.get(4), String.valueOf(c.isSelected()));

            // Apply reweighting
            instance.setWeight(c.getWeight() * 10);

            data.add(instance);
        }

        return data;
    }
}
