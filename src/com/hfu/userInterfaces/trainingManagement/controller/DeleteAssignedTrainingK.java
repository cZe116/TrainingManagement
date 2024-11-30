package com.hfu.userInterfaces.trainingManagement.controller;

import com.hfu.userInterfaces.trainingManagement.model.Clerk;
import com.hfu.userInterfaces.trainingManagement.model.Training;
import java.util.Collection;

public class DeleteAssignedTrainingK {
    private String checkTrainingDeletion(Clerk clerk, Training training) {
        Collection<Training> completedTraining = clerk.getPassedTrainings();

        for (Training passedTrainings : completedTraining) {
            if (training.isPrerequisiteOf(passedTrainings)) {
                return "The training " + training.getName() + " is a dependency "
                        + " from the training " + passedTrainings.getName() + ". Therefor it cannot"
                        + "be deleted for " + clerk.getUsername() + ".";
            }
        }

        return null;
    }

    String deleteAssignedTraining(String clerkName, String trainingName) {
        Clerk clerk = Clerk.get(clerkName);
        Training training = Training.get(trainingName);

        if (clerk.attendsTraining(training)) {
            clerk.removeAttendedTraining(training);
            return null;
        } else {
            String error = checkTrainingDeletion(clerk, training);
            if (error == null) {
                clerk.removePassedTraining(training);
                return null;
            } else {
                throw new RuntimeException(error);
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
