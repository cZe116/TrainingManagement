package com.hfu.userInterfaces.trainingManagement.controller;

import com.hfu.userInterfaces.trainingManagement.model.Clerk;
import com.hfu.userInterfaces.trainingManagement.model.Training;

public class AssignTrainingK {

    void attendTraining(String clerkName, String trainingName) {
        Training training = Training.get(trainingName);
        Clerk clerk = Clerk.get(clerkName);
        clerk.assignTraining(training);
    }

    void completeTraining(String clerkName, String trainingName) {
        Training training = Training.get(trainingName);
        Clerk clerk = Clerk.get(clerkName);
        clerk.passTraining(training);
    }

}
