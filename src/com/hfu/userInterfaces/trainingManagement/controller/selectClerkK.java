package com.hfu.userInterfaces.trainingManagement.controller;

import com.hfu.userInterfaces.trainingManagement.model.Clerk;

public class selectClerkK {

    public String[] getClerkNames(){
        if(!Clerk.theClerks.isEmpty()){
            return Clerk.theClerks.keySet().toArray(new String[0]);
        } else {
            throw new NullPointerException("There is no clerk to be selected!");
        }
    }

}
