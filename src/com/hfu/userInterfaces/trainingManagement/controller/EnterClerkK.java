package com.hfu.userInterfaces.trainingManagement.controller;

import com.hfu.userInterfaces.trainingManagement.model.Clerk;
import java.util.NoSuchElementException;

public class EnterClerkK {
    public static void generateClerk(String name, String password, boolean isAdmin){
        Clerk clerk = new Clerk(name, password, isAdmin);
        Clerk.theClerks.put(clerk.getUsername(), clerk);
        if (!Clerk.theClerks.containsKey(name)){
            throw new NoSuchElementException("Clerk : " + name + " couldn't be found in map after creation!");
        }
    }
}
