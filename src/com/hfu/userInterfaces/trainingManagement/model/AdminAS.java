package com.hfu.userInterfaces.trainingManagement.model;

import com.hfu.userInterfaces.trainingManagement.controller.DeleteClerkK;
import com.hfu.userInterfaces.trainingManagement.controller.EditClerkK;
import com.hfu.userInterfaces.trainingManagement.controller.EnterClerkK;
import com.hfu.userInterfaces.trainingManagement.util.Entry;

import java.util.Scanner;

public class AdminAS extends NormalAS{

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void showMenu() {
        System.out.println();
        System.out.println("Select from one of the following options: ");
        System.out.println("(1) Edit a clerk");
        System.out.println("(2) Assign a training");
        System.out.println("(3) Remove an assigned training");
        System.out.println("(4) Show assigned training");
        System.out.println("(5) Create a clerk");
        System.out.println("(6) Delete a clerk");
        System.out.println("(7) Logout");
        System.out.println("(8) Exit");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1 -> {
                String clerkToBeEdited = Entry.getAnswer("Please enter the username of the clerk to be edited: ");
                editClerk(clerkToBeEdited);
                showMenu();
            }
            case 2 -> {
                String clerkToAssignATraining = Entry.getAnswer("Please enter the username of the clerk to assign a training to: ");
                String trainingNameToAssign = Entry.getAnswer("Please enter the name of the training to assign: ");
                assignTraining(clerkToAssignATraining, trainingNameToAssign);
                showMenu();
            }
            case 3 -> {
                String clerkToRemoveAssignment = Entry.getAnswer("Please enter the username of the clerk to remove an assigned training from: ");
                String trainingNameToRemove = Entry.getAnswer("Please enter the name of the training that is to be removed: ");
                removeAssignedTraining(clerkToRemoveAssignment, trainingNameToRemove);
                showMenu();
            }
            case 4 -> {
                String clerkToShowAssignments = Entry.getAnswer("Please enter the username of the clerk to show the assigned trainings from: ");
                showAssignedTraining(clerkToShowAssignments);
                showMenu();
            }
            case 5 -> {
                String usernameOfClerkToBeCreated = Entry.getAnswer("Please enter the username of the clerk that is to be created: ");
                String passwordOfClerkToBeCreated = Entry.getAnswer("Please enter the password of the clerk that is to be created: ");
                String adminOptionOfClerkToBeCreated = Entry.getAnswer("Please enter if the clerk that is to be created is an admin (Y/N): ");
                if(adminOptionOfClerkToBeCreated.equalsIgnoreCase("Y")){
                    createClerk(usernameOfClerkToBeCreated, passwordOfClerkToBeCreated, true);
                } else if (adminOptionOfClerkToBeCreated.equalsIgnoreCase("N")) {
                    createClerk(usernameOfClerkToBeCreated, passwordOfClerkToBeCreated, false);
                }

                showMenu();
            }
            case 6 -> {
                String clerkToBeDeleted = Entry.getAnswer("Please enter the username of the clerk to be deleted: ");
                deleteClerk(clerkToBeDeleted);
                showMenu();
            }
            case 7 -> {
                System.out.println("Logging out...");
                TrainingManagementHS.trainingManagement.login();
            }
            case 8 -> {
                Clerk.save();
                System.exit(0);
            }
            default -> {
                System.out.println("Invalid option! Please try again.");
                showMenu();
            }
        }
    }


    @Override
    public void editClerk(String username){

        Clerk tempClerk = Clerk.get(username);

        String newUserName = "", newPassword = "";

        String editClerkAnswerUserName = Entry.getAnswer("Want to change the username ? (Y/N): ");

        if(editClerkAnswerUserName.equalsIgnoreCase("Y")){
            newUserName = Entry.getAnswer("Please enter the new username: ");
        }

        String editClerkAnswerPassword = Entry.getAnswer("Want to change the password ? (Y/N): ");

        if(editClerkAnswerPassword.equalsIgnoreCase("Y")){
            newPassword = Entry.getAnswer("Please enter the new password: ");
        }

        String editClerkAnswerAdmin = Entry.getAnswer("Is the user to be considered an admin ? (Y/N): ");

        if(editClerkAnswerAdmin.equalsIgnoreCase("Y")){
            try{
                EditClerkK.editClerk(tempClerk.getUsername(), newUserName.isEmpty() ? tempClerk.getUsername() : newUserName, newPassword.isEmpty() ? tempClerk.getPassword() : newPassword, true);
            } catch(Exception e){
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Entering options now!");
                System.out.println();
                showMenu();
            }
        } else if (editClerkAnswerAdmin.equalsIgnoreCase("N")) {
            try{
                EditClerkK.editClerk(tempClerk.getUsername(), newUserName.isEmpty() ? tempClerk.getUsername() : newUserName, newPassword.isEmpty() ? tempClerk.getPassword() : newPassword, false);
            } catch(Exception e){
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Entering options now!");
                System.out.println();
                showMenu();
            }
        }
    }

    public void createClerk(String username, String password, boolean isAdmin){
        try{
            EnterClerkK.generateClerk(username, password, isAdmin);
            System.out.println("Created clerk: " + username + " successfully!");
            showMenu();
        }catch(Exception e){
            System.out.println("An error occurred: " + e.getMessage());
            System.out.println("Entering admin options now!");
            System.out.println();
            showMenu();
        }
    }

    public void deleteClerk(String clerkName){
        try{
            DeleteClerkK.deleteClerk(clerkName);
            System.out.println("Deleted clerk: " + clerkName + " successfully!");
            showMenu();
        }catch(Exception e){
            System.out.println("An error occurred: " + e.getMessage());
            System.out.println("Entering admin options now!");
            System.out.println();
            showMenu();
        }
    }
}
