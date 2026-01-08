package com.project.resume;

public class Candidate {

    private int skillCount;
    private int experienceYears;
    private String education;
    private boolean careerGap;
    private String gender;
    private String collegeTier;
    private int age;
    private String location;
    private boolean selected;

    // fairness fields
    private double weight = 1.0;
    private double fairScore = 0.0;

    public Candidate(
            int skillCount,
            int experienceYears,
            String education,
            boolean careerGap,
            String gender,
            String collegeTier,
            int age,
            String location,
            boolean selected
    ) {
        this.skillCount = skillCount;
        this.experienceYears = experienceYears;
        this.education = education;
        this.careerGap = careerGap;
        this.gender = gender;
        this.collegeTier = collegeTier;
        this.age = age;
        this.location = location;
        this.selected = selected;
        
        this.weight = 1.0;
        this.fairScore = 0.0;
    }

    // ---------- Getters ----------
    public int getSkillCount() { return skillCount; }
    public int getExperienceYears() { return experienceYears; }
    public String getEducation() { return education; }
    public boolean hasCareerGap() { return careerGap; }
    public String getGender() { return gender; }
    public String getCollegeTier() { return collegeTier; }
    public int getAge() { return age; }
    public String getLocation() { return location; }
    public boolean isSelected() { return selected; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getFairScore() { return fairScore; }
    public void setFairScore(double fairScore) { this.fairScore = fairScore; }
}
