package com.hfu.userInterfaces.trainingManagement.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Training {
    private final String name;
    private final Set<Training> requirements = new HashSet<>();
    public static Map<String, Training> theTrainings = new HashMap<>();

    public Training(String name) {
        this.name = name;
        theTrainings.put(name, this);
    }

    public Training(String name, Training... vorausgesetzteFortbildungen) {
        this.name = name;

        for (Training f : vorausgesetzteFortbildungen) {
            requirements.add(f);
        }

        theTrainings.put(this.getName(), this);
    }

    public boolean isPrerequisiteOf(Training training) {
        return training.getAllPrerequisites().contains(this);
    }

    public static String[] giveAllNames() {
        return theTrainings.keySet().toArray(new String[0]);
    }

    public static Training get(String name) {
        return theTrainings.get(name);
    }

    public String getName() {
        return name;
    }

    public void addPrerequisites(Training training) {
        requirements.add(training);
    }

    public void removePrerequisites(Training training) {
        requirements.remove(training);
    }

    public Set<Training> getAllPrerequisites() {
        return requirements;
    }
}
