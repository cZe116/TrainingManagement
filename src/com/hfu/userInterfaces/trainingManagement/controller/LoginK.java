package com.hfu.userInterfaces.trainingManagement.controller;

import com.hfu.userInterfaces.trainingManagement.exceptions.InsufficientPrivilegeException;
import com.hfu.userInterfaces.trainingManagement.model.Clerk;
import java.util.NoSuchElementException;

public class LoginK {
    public static void passwordMatchesUsername(String username, String password) {
        Clerk clerk = Clerk.get(username);
        String errorMessage = "Login failed";
        if (clerk == null) {
            throw new NoSuchElementException(errorMessage);
        } else {
            String passwordOfClerk = clerk.getPassword();
            if (!passwordOfClerk.equals(password)) {
                throw new NoSuchElementException(errorMessage);
            }
        }
    }

    public static void privilegesMatchClerk(String username, boolean isAdmin) {
        if(isAdmin) {
            Clerk clerk = Clerk.get(username);
            if (!clerk.isAdmin()) {
                throw new InsufficientPrivilegeException("You don't have administrator privileges!");
            }
        }
    }

    boolean isAdmin(String username) {
        return Clerk.get(username).isAdmin();
    }
}
