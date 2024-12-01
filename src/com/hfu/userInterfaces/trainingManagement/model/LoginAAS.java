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
                AdminAS admin = new AdminAS();
                admin.showMenu();
            } else {
                NormalAS normal = new NormalAS();
                normal.showMenu();
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            System.out.println("Entering login now!");
            System.out.println();
            TrainingManagementHS trainingManagement = new TrainingManagementHS();

        }
    }
}
