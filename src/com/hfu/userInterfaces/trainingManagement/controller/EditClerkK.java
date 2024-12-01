package com.hfu.userInterfaces.trainingManagement.controller;

import com.hfu.userInterfaces.trainingManagement.model.Clerk;
import java.util.NoSuchElementException;

public class EditClerkK {
    public static void editClerk(String oldName, String newName, String password, boolean isAdmin) {
        Clerk currentClerk = Clerk.theClerks.get(oldName);
        System.out.println(currentClerk);

        if(currentClerk != null){
            currentClerk.setUsername(newName);
            currentClerk.setPassword(password);
            currentClerk.setAdmin(isAdmin);

            Clerk.theClerks.remove(oldName);
            Clerk.theClerks.put(newName, currentClerk);
        }
        throw new NoSuchElementException("Couldn't find clerk : " + oldName + "!");
    }
}