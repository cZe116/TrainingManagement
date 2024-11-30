package com.hfu.userInterfaces.trainingManagement.model;

import com.hfu.userInterfaces.trainingManagement.controller.DeleteClerkK;
import com.hfu.userInterfaces.trainingManagement.controller.EditClerkK;
import com.hfu.userInterfaces.trainingManagement.controller.EnterClerkK;

import java.util.Scanner;

public class AdminAS extends NormalAS{

    private Scanner scanner = new Scanner(System.in);

    public AdminAS(){

        System.out.println();
        System.out.println("Select from one of the following options: ");
        System.out.println("(1) Edit a clerk");
        System.out.println("(2) Assign a training");
        System.out.println("(3) Remove an assigned training");
        System.out.println("(4) Show assigned training");
        System.out.println("(5) Create a clerk");
        System.out.println("(6) Delete a clerk");
        System.out.println("(7) Logout");
        int option = scanner.nextInt();

        if(option == 1){
            System.out.print("Please enter the username of the clerk to edited: ");
            String clerkToBeEdited = scanner.nextLine();
            editClerk(clerkToBeEdited);
        } else if (option == 2) {
            System.out.print("Please enter the username of the clerk to assign a training to: ");
            String clerkToAssignATraining = scanner.nextLine();
            System.out.print("Please enter the name of the training to assign: ");
            String trainingNameToAssign = scanner.nextLine();
            assignTraining(clerkToAssignATraining, trainingNameToAssign);
        } else if (option == 3) {
            System.out.print("Please enter the username of the clerk ro remove an assigned training from: ");
            String clerkToRemoveAssignment = scanner.nextLine();
            System.out.print("Please enter the name of the training that is to be removed: ");
            String trainingNameToRemove = scanner.nextLine();
            removeAssignedTraining(clerkToRemoveAssignment, trainingNameToRemove);
        } else if (option == 4) {
            System.out.print("Please enter the username of the clerk to show the assigned trainings from: ");
            String clerkToShowAssignments = scanner.nextLine();
            showAssignedTraining(clerkToShowAssignments);
        } else if (option == 5) {
            System.out.print("Please enter the username of the clerk that is to be created: ");
            String usernameOfClerkToBeCreated = scanner.nextLine();
            System.out.print("Please enter the password of the clerk that is to be created: ");
            String passwordOfClerkToBeCreated = scanner.nextLine();
            System.out.print("Please enter if the clerk that is to be created is an admin (Y/N): ");
            String adminOptionOfClerkToBeCreated = scanner.nextLine();
            boolean convertedAdminOption = false;
            if(adminOptionOfClerkToBeCreated.equalsIgnoreCase("Y")){
                convertedAdminOption = true;
            } else if (adminOptionOfClerkToBeCreated.equalsIgnoreCase("N")) {
                convertedAdminOption = false;
            }
            createClerk(usernameOfClerkToBeCreated, passwordOfClerkToBeCreated, convertedAdminOption);
        } else if (option == 6) {
            System.out.print("Please enter the username of the clerk to be deleted: ");
            String clerkToBeDeleted = scanner.nextLine();
            deleteClerk(clerkToBeDeleted);
        } else if (option == 7) {
            TrainingManagementHS trainingManagement = new TrainingManagementHS();
        }

    }

    @Override
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

        System.out.print("Is the user to be considered an admin ? (Y/N): ");
        String editClerkAnswerAdmin = scanner.nextLine();

        if(editClerkAnswerAdmin.equalsIgnoreCase("Y")){
            try{
                EditClerkK.editClerk(tempClerk.getUsername(), newUserName.isEmpty() ? tempClerk.getUsername() : newUserName, newPassword.isEmpty() ? tempClerk.getPassword() : newPassword, true);
            } catch(Exception e){
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Entering options now!");
                System.out.println();
                NormalAS normal = new NormalAS();
            }
        } else if (editClerkAnswerAdmin.equalsIgnoreCase("N")) {
            try{
                EditClerkK.editClerk(tempClerk.getUsername(), newUserName.isEmpty() ? tempClerk.getUsername() : newUserName, newPassword.isEmpty() ? tempClerk.getPassword() : newPassword, false);
            } catch(Exception e){
                System.out.println("An error occurred: " + e.getMessage());
                System.out.println("Entering options now!");
                System.out.println();
                NormalAS normal = new NormalAS();
            }
        }
    }

    public void createClerk(String username, String password, boolean isAdmin){
        try{
            EnterClerkK.generateClerk(username, password, isAdmin);
            System.out.println("Created clerk: " + username + " successfully!");
            AdminAS admin = new AdminAS();
        }catch(Exception e){
            System.out.println("An error occurred: " + e.getMessage());
            System.out.println("Entering admin options now!");
            System.out.println();
            AdminAS admin = new AdminAS();
        }
    }

    public void deleteClerk(String clerkName){
        try{
            DeleteClerkK.deleteClerk(clerkName);
            System.out.println("Deleted clerk: " + clerkName + " successfully!");
            AdminAS admin = new AdminAS();
        }catch(Exception e){
            System.out.println("An error occurred: " + e.getMessage());
            System.out.println("Entering admin options now!");
            System.out.println();
            AdminAS admin = new AdminAS();
        }
    }
}
