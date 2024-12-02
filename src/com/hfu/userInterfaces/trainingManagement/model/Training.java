package com.hfu.userInterfaces.trainingManagement.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Training implements Serializable {

    @Serial
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

    private final String name;
    private final Set<Training> dependencies = new LinkedHashSet<>();

    public Training(String name, Training... dependentTrainings) {
        this.name = name;

        dependencies.addAll(Arrays.asList(dependentTrainings));

        theTrainings.put(this.getName(), this);
    }

    public String getName() {
        return this.name;
    }

    public boolean isDependencyOf(Training training) {
        return training.dependencies.contains(this);
    }

    public Set<Training> getDependencies() {
        return dependencies;
    }


}