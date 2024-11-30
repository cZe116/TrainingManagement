package com.hfu.userInterfaces.trainingManagement.controller;

import com.hfu.userInterfaces.trainingManagement.model.Training;

public class selectTrainingK {

    public String[] getTrainingNames(){
        if(!Training.theTrainings.isEmpty()){
            return Training.theTrainings.keySet().toArray(new String[0]);
        } else {
            throw new NullPointerException("There is no training to be selected!");
        }
    }

}
