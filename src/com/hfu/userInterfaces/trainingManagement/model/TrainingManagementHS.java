package com.hfu.userInterfaces.trainingManagement.model;

import com.hfu.userInterfaces.trainingManagement.util.Entry;

import java.util.Scanner;

public class TrainingManagementHS {

    public static void main(String[] args) {
        Training maths1 = new Training("Maths 1");
        Training commonBWL = new Training("Common business studies");
        Training maths2 = new Training("Mathematik 2", maths1);
        Training kostenRechnung = new Training("Kostenrechnung", maths2, commonBWL);
        try{
            Clerk admin = new Clerk("Admin", "aaAA11&&aa", true);
            TrainingManagementHS trainingManagement = new TrainingManagementHS();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private String username, password;
    private boolean isAdmin;

    public void open(){
        LoginAAS login = new LoginAAS(username, password, isAdmin);
    }

    public TrainingManagementHS(){
        System.out.println("Welcome to the trainingmanagement.");
        System.out.println("Please login to a currently existing user:");
        username = Entry.getAnswer("Username: ");
        password = Entry.getAnswer("Password: ");
        String answer = Entry.getAnswer("Want to login as admin ? (Y/N)");
        if(username != null && password != null && answer != null){
            if(answer.equalsIgnoreCase("Y")){
                isAdmin = true;
            } else if (answer.equalsIgnoreCase("N")) {
                isAdmin = false;
            }

            open();

        } else {
            System.out.println("At least one parameter missing!");
            System.out.println("Please retry after restart of program!");
            System.out.println();

            TrainingManagementHS trainingManagementHS = new TrainingManagementHS();
        }




    }
}
