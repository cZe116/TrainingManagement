package com.hfu.userInterfaces.trainingManagement.model;

import com.hfu.userInterfaces.trainingManagement.controller.LoginK;

public class LoginAAS {

    private String username, password;
    private boolean isAdmin = false;

    public LoginAAS(String username, String password, boolean isAdmin){
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;

        open();
    }

    public void open(){
        try {
            LoginK.passwordMatchesUsername(username, password);
            LoginK.privilegesMatchClerk(username, isAdmin);
            if(isAdmin){
                System.out.println("DEBUG");
                AdminAS admin = new AdminAS();
            } else {
                NormalAS normal = new NormalAS();
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            System.out.println("Entering login now!");
            System.out.println();
            TrainingManagementHS trainingManagement = new TrainingManagementHS();

        }
    }
}
