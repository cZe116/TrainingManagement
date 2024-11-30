package com.hfu.userInterfaces.trainingManagement.controller;

import com.hfu.userInterfaces.trainingManagement.model.Clerk;

public class ClerkK {
    static String getPassword(String username) {
        return Clerk.get(username).getPassword();
    }

    static boolean getPrivileges(String username) {
        return Clerk.get(username).isAdmin();
    }
}
