package com.synergykit.sampleapp.model;

import com.synergykit.sdk.resources.SynergyKitObject;

/**
 * Created by Marek on 1/14/15.
 */
public class CloudCodeDemo extends SynergyKitObject {
    private String path;

    private int age;
    private int ageRange;
    private String race;
    private float raceConfidence;
    private String gender;
    private float genderConfidence;
    private float smiling;
    private String glass;
    private float glassConfidence;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAgeRange() {
        return ageRange;
    }

    public void setAgeRange(int ageRange) {
        this.ageRange = ageRange;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public float getRaceConfidence() {
        return raceConfidence;
    }

    public void setRaceConfidence(float raceConfidence) {
        this.raceConfidence = raceConfidence;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public float getGenderConfidence() {
        return genderConfidence;
    }

    public void setGenderConfidence(float genderConfidence) {
        this.genderConfidence = genderConfidence;
    }

    public float getSmiling() {
        return smiling;
    }

    public void setSmiling(float smiling) {
        this.smiling = smiling;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public float getGlassConfidence() {
        return glassConfidence;
    }

    public void setGlassConfidence(float glassConfidence) {
        this.glassConfidence = glassConfidence;
    }
}
