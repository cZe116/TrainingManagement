package com.hfu.userInterfaces.trainingManagement.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Training implements Serializable {

    private static final long serialVersionUID = 1L;
    public static Map<String, Training> theTrainings = new HashMap<>();

    static Map<String, Training> getTrainings() {
        return theTrainings;
    }

    static void setTheTrainings(Map<String, Training> trainings) {
        theTrainings = trainings;
    }

    static {
        Training mathematik1 = new Training("Mathematik 1");
        Training allgemeineBwl = new Training("Allgemeine BWL");
        Training mathematik2 = new Training("Mathematik 2", mathematik1);
        new Training("Kostenrechnung", mathematik2, allgemeineBwl);
    }

    static String[] getAllTrainingNames() {
        return theTrainings.keySet().toArray(new String[0]);
    }

    public static Training get(String name) {
        return theTrainings.get(name);
    }

    private String name;
    private Set<Training> dependencies = new LinkedHashSet<>();

    Training(String name, Training... dependentTrainings) {
        this.name = name;

        for (Training f : dependentTrainings) {
            dependencies.add(f);
        }

        theTrainings.put(this.getName(), this);
    }

    public String getName() {
        return this.name;
    }

    public boolean isDependencyOf(Training fortbildung) {
        return fortbildung.dependencies.contains(this);
    }

    public Set<Training> getDependencies() {
        return dependencies;
    }


}