package com.hfu.userInterfaces.trainingManagement.controller;

public class ShowAssignedTrainingsK {

    String[] getCompletedTrainingNames(String username) {
        return new SelectAssignedTrainingK().getPassedTrainings(username);
    }

    String[] getAssignedTrainingNames(String username) {
        return new SelectAssignedTrainingK().getAttendedTrainings(username);
    }

}
