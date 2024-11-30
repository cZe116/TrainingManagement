package com.hfu.userInterfaces.trainingManagement.model;

import com.hfu.userInterfaces.trainingManagement.controller.EditClerkK;
import com.hfu.userInterfaces.trainingManagement.util.Entry;

import java.util.Collection;
import java.util.Scanner;

public class NormalAS {

    private Scanner scanner = new Scanner(System.in);

    public NormalAS(){

        System.out.println();
        System.out.println("Select from one of the following options: ");
        System.out.println("(1) Edit a clerk");
        System.out.println("(2) Assign a training");
        System.out.println("(3) Remove an assigned training");
        System.out.println("(4) Show assigned training");
        System.out.println("(5) Logout");
        int option = scanner.nextInt();

        if(option == 1){
            String clerkToBeEdited = Entry.getAnswer("Please enter the username of the clerk to edited: ");
            editClerk(clerkToBeEdited);
        } else if (option == 2) {
            String clerkToAssignATraining = Entry.getAnswer("Please enter the username of the clerk to assign a training to: ");
            String trainingNameToAssign = Entry.getAnswer("Please enter the name of the training to assign: ");
            assignTraining(clerkToAssignATraining, trainingNameToAssign);
        } else if (option == 3) {
            String clerkToRemoveAssignment = Entry.getAnswer("Please enter the username of the clerk ro remove an assigned training from: ");
            String trainingNameToRemove = Entry.getAnswer("Please enter the name of the training that is to be removed: ");
            removeAssignedTraining(clerkToRemoveAssignment, trainingNameToRemove);
        } else if (option == 4) {
            String clerkToShowAssignments = Entry.getAnswer("Please enter the username of the clerk to show the assigned trainings from: ");
            showAssignedTraining(clerkToShowAssignments);
        } else if (option == 5) {
            TrainingManagementHS trainingManagement = new TrainingManagementHS();
        }

    }

    public void editClerk(String username){

        Clerk tempClerk = Clerk.get(username);

        String newUserName = "", newPassword = "";

        System.out.print("Want to change the username ? (Y/N): ");
        String editClerkAnswerUserName = scanner.nextLine();

        if(editClerkAnswerUserName.equalsIgnoreCase("Y")){
            System.out.print("Please enter the new username: ");
            newUserName = scanner.nextLine();
        }

        System.out.print("Want to change the password ? (Y/N): ");
        String editClerkAnswerPassword = scanner.nextLine();

        if(editClerkAnswerUserName.equalsIgnoreCase("Y")){
            System.out.print("Please enter the new password: ");
            newPassword = scanner.nextLine();
        }

        try{
            EditClerkK.editClerk(tempClerk.getUsername(), newUserName.isEmpty() ? tempClerk.getUsername() : newUserName, newPassword.isEmpty() ? tempClerk.getPassword() : newPassword, tempClerk.isAdmin());
        } catch(Exception e){
            System.out.println("An error occurred: " + e.getMessage());
            System.out.println("Entering options now!");
            System.out.println();
            NormalAS normal = new NormalAS();
        }
    }

    public void assignTraining(String username, String trainingName){
        Clerk clerkForAssignment = Clerk.get(username);
        System.out.println(Clerk.get(username).getUsername());
        Training trainingForAssignment = Training.get(trainingName);
        clerkForAssignment.attendTraining(trainingForAssignment);
    }

    public void removeAssignedTraining(String username, String trainingName){
        Clerk clerkForAssignment = Clerk.get(username);
        Training trainingForAssignment = Training.get(trainingName);
        clerkForAssignment.removeAttendedTraining(trainingForAssignment);
    }

    public void showAssignedTraining(String username){
        Clerk clerkToShowAssignments = Clerk.get(username);
        Collection<Training> assignments = clerkToShowAssignments.getAttendedTrainings();
        for(Training assignment : assignments){
            System.out.println("Assigned to training : " + assignment.getName());
        }
    }
}
