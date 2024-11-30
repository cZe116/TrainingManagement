package com.hfu.userInterfaces.trainingManagement.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Clerk implements Serializable {
    private String username, password;
    private boolean isAdmin;
    private final Map<String, Training> passedTrainings = new HashMap<>();
    private Map<String, Training> attendedTrainings = new HashMap<>();
    public static Map<String, Clerk> theClerks = new HashMap<>();

    public Clerk(String username, String password, boolean isAdmin) {
        try{
            this.username = username;
            checkPassword(password);
            this.password = password;
            this.isAdmin = isAdmin;
            theClerks.put(username, this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] giveAllNames() {
        return theClerks.keySet().toArray(new String[0]);
    }

    public Collection<Training> getPassedTrainings() {
        return passedTrainings.values();
    }

    public Collection<Training> getAttendedTrainings() {
        return attendedTrainings.values();
    }

    public void removeAttendedTraining(Training training) {
        attendedTrainings.remove(training);
    }

    public void removePassedTraining(Training training) {
        passedTrainings.remove(training);
    }

    public boolean attendsTraining(Training training) {
        return attendedTrainings.containsKey(training.getName());
    }

    public void setPermissions(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public static Clerk get(String username) {
        return theClerks.get(username);
    }

    public static void addClerk(Clerk clerk) {
        theClerks.put(clerk.username, clerk);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public static boolean deleteClerk(String username) {
        return theClerks.remove(username) != null;
    }

    public static boolean checkDelete(String username) {
        return theClerks.containsKey(username);
    }

    public boolean checkUsername(String username) {
        return this.username.equals(username);
    }

    public static void checkPassword(String password) {
        String regEx = ".*[A-ZÄÜÖ].*[A-ZÄÜÖ].*";
        boolean matches = password.matches(regEx);
        regEx = ".*[a-zäüöß].*[a-zäüöß].*";
        matches &= password.matches(regEx);
        regEx = ".*\\d.*\\d.*";
        matches &= password.matches(regEx);
        String special = "[^a-zäüößA-ZÄÜÖ0-9]";
        regEx = ".*" + special + ".*" + special + ".*";
        matches &= password.matches(regEx);
        matches &= password.length() >= 10;
        if (!matches) {
            throw new RuntimeException("Password doesnt match requirements!") ;
        }
    }

    public static boolean atLeastOneEmployeeExists() {
        return !theClerks.isEmpty();
    }

    public static boolean atLeastOneAdminExists() {
        return theClerks.values().stream().anyMatch(s -> s.isAdmin);
    }

    public static boolean atLeastOneTrainingAssignmentExists() {
        return theClerks.values().stream().anyMatch(s -> !s.attendedTrainings.isEmpty());
    }

    public void attendTraining(Training training) {
        attendedTrainings.put(training.getName(), training);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void completeTraining(Training training) {
        attendedTrainings.remove(training.getName());
        passedTrainings.put(training.getName(), training);
    }

    public void removeTraining(Training training) {
        attendedTrainings.remove(training.getName());
        passedTrainings.remove(training.getName());
    }
}
