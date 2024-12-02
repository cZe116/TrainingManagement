package com.hfu.userInterfaces.trainingManagement.model;

import com.hfu.userInterfaces.trainingManagement.util.Entry;

public class TrainingManagementHS {

    public static TrainingManagementHS trainingManagement;

    public TrainingManagementHS() {
    }

    public static void main(String[] args) {
        try{
            Clerk.read();
            trainingManagement = new TrainingManagementHS();
            trainingManagement.login();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private String username, password;
    private boolean isAdmin;

    public void open(){
        LoginAAS login = new LoginAAS(username, password, isAdmin);
    }

    public void login(){
        Clerk.printAllClerkNames();
        System.out.println("Welcome to the training management.");
        System.out.println("Please login to a currently existing user:");
        username = Entry.getAnswer("Username: ");
        password = Entry.getAnswer("Password: ");
        String answer = Entry.getAnswer("Want to login as admin ? (Y/N): ");
        if(username != null && !username.isEmpty() && password != null && !password.isEmpty() && answer != null && !answer.isEmpty()){
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

            trainingManagement.login();
        }
    }
}
