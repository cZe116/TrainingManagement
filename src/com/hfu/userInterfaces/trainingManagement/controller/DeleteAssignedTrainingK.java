package com.hfu.userInterfaces.trainingManagement.controller;

import com.hfu.userInterfaces.trainingManagement.model.Clerk;
import com.hfu.userInterfaces.trainingManagement.model.Training;
import java.util.Collection;

public class DeleteAssignedTrainingK {
    private void checkTrainingDeletion(Clerk clerk, Training training) {
        Collection<Training> completedTraining = clerk.getPassedTrainings();

        for (Training passedTrainings : completedTraining) {
            if (training.isDependencyOf(passedTrainings)) {
                throw new RuntimeException("The training " + training.getName() + " is a dependency from " + passedTrainings.getName() + ". Therefor it cannot be deleted for " + clerk.getUsername() + "!");
            }
        }
    }

    private void deleteAssignedTraining(String clerkName, String trainingName) {
        Clerk clerk = Clerk.get(clerkName);
        Training training = Training.get(trainingName);

        if (clerk.isAssigned(training)) {
            clerk.deleteAssignedTraining(training);
        } else {
            try{
                checkTrainingDeletion(clerk, training);
                clerk.deletePassedTrainings(training);
            }catch(Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    String[] getAssignedTrainings(String clerkName) {
        return new ShowAssignedTrainingsK().getAssignedTrainingNames(clerkName);
    }

    String[] getCompletedTrainings(String clerkName) {
        return new ShowAssignedTrainingsK().getCompletedTrainingNames(clerkName);
    }
}
