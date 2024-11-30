package com.hfu.userInterfaces.trainingManagement.controller;

import com.hfu.userInterfaces.trainingManagement.model.Clerk;

public class DeleteClerkK {
    public static void deleteClerk(String clerkName) {
        Clerk.theClerks.remove(clerkName);
        if(Clerk.theClerks.containsKey(clerkName)){
            throw new RuntimeException("Couldn't delete clerk : " + clerkName + "!");
        }
    }
}
