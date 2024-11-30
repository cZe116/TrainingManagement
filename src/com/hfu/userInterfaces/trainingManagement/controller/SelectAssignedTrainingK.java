package com.hfu.userInterfaces.trainingManagement.controller;

import com.hfu.userInterfaces.trainingManagement.model.Clerk;
import com.hfu.userInterfaces.trainingManagement.model.Training;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class SelectAssignedTrainingK {

    public String[] getPassedTrainings(String username) {
        Clerk clerk = Clerk.get(username);
        return convertToString(clerk.getPassedTrainings()).toArray(new String[0]);
    }

    public String[] getAttendedTrainings(String username) {
        Clerk clerk = Clerk.get(username);
        return convertToString(clerk.getAttendedTrainings()).toArray(new String[0]);
    }

    private Set<String> convertToString(Collection<Training> trainings) {
        Set<String> strings = new LinkedHashSet<>();

        for (Training training : trainings) {
            strings.add(training.getName());
        }

        return strings;
    }

}
