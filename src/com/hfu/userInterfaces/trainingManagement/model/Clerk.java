package com.hfu.userInterfaces.trainingManagement.model;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Clerk implements Serializable {

    private static final long serialVersionUID = 1L;
    public static Map<String, Clerk> theClerks = new HashMap<>();

    static String[] getAllClerkNames() {
        return theClerks.keySet().toArray(new String[0]);
    }

    public static Clerk get(String username) {
        return theClerks.get(username);
    }

    private static void addClerk(Clerk clerk) {
        theClerks.put(clerk.getUsername(), clerk);
    }

    static boolean checkClerkExists() {
        return !theClerks.isEmpty();
    }

    static boolean checkAdminExistence() {

        for (Clerk s : theClerks.values()) {
            if (s.isAdmin) {
                return true;
            }
        }

        return false;
    }

    static void checkUsername(String username) {
        boolean isOkay = !theClerks.containsKey(username);

        if (!isOkay) {
            throw new RuntimeException("Username " + username + " does exist already!");
        }

        String regEx = "[a-zäüößA-ZÄÜÖ_]+";
        isOkay = username.matches(regEx);
        if (!isOkay) {
            throw new RuntimeException("The username doesnt comply with the specifications!");
        }
    }

    static void checkPassword(String passwort) {
        String regEx = ".*[A-ZÄÜÖ].*[A-ZÄÜÖ].*";
        boolean isOkay = passwort.matches(regEx);
        regEx = ".*[a-zäüöß].*[a-zäüöß].*";
        isOkay &= passwort.matches(regEx);
        regEx = ".*\\d.*\\d.*";
        isOkay &= passwort.matches(regEx);
        String sonderzeichen = "[^a-zäüößA-ZÄÜÖ0-9]";
        regEx = ".*" + sonderzeichen + ".*" + sonderzeichen + ".*";
        isOkay &= passwort.matches(regEx);
        isOkay &= passwort.length() >= 10;
        if (!isOkay) {
            throw new RuntimeException("The password doesnt comply with the specifications!");
        }
    }

    public static void create(String username, String password, boolean isAdmin) {
        try{
            checkUsername(username);
            checkPassword(password);
            Clerk c = new Clerk(username, password, isAdmin);
            addClerk(c);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    private String username;
    private String password;
    private boolean isAdmin = false;

    private Map<String, Training> passedTrainings = new HashMap<>();
    private Map<String, Training> attendedTrainings = new HashMap<>();

    boolean isPassed(Training training) {
        return passedTrainings.containsKey(training.getName());
    }

    public boolean isAssigned(Training fortbildung) {
        return attendedTrainings.containsKey(fortbildung.getName());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String newUsername) {

        try{
            if (!newUsername.equals(this.username)) {
                checkUsername(newUsername);
                theClerks.remove(this.username);
                this.username = newUsername;
                theClerks.put(newUsername, this);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        try{
            checkPassword(password);
            this.password = password;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        boolean oldPriv = this.isAdmin;
        this.isAdmin = isAdmin;
        if (!checkAdminExistence()) {
            this.isAdmin = oldPriv;
            throw new RuntimeException("The clerk " + getUsername() + " is the last administrator, it cannot be reduced in privileges!");
        }
    }

    public Clerk(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public boolean atLeastOneTrainingAssigned() {
        boolean exists = !this.attendedTrainings.isEmpty();
        return exists |= !this.passedTrainings.isEmpty();
    }

    public void delete() {
        theClerks.remove(getUsername());
        if (!checkAdminExistence()) {
            theClerks.put(getUsername(), this);
            throw new RuntimeException("The clerk " + getUsername() + " is the last administrator, it cannot be deleted!");
        }
    }

    public void assignTraining(Training training) {
        attendedTrainings.put(training.getName(), training);
    }

    public void passTraining(Training training) {
        attendedTrainings.remove(training.getName());
        passedTrainings.put(training.getName(), training);
    }

    public void deleteAssignedTraining(Training training) {
        attendedTrainings.remove(training.getName());
    }

    public void deletePassedTrainings(Training training) {
        passedTrainings.remove(training.getName());
    }

    public static void main(String[] args) {
        String name = " ";

        create(name, "gGG+2&256g", true);
        get("admin").delete();
        get(name).delete();
        printAllClerkNames();
    }

    static void printAllClerkNames() {
        String[] allNames = Clerk.getAllClerkNames();
        for (String name : allNames) {
            System.out.println(name);
        }

    }

    public Collection<Training> getPassedTrainings() {
        return passedTrainings.values();
    }

    public Collection<Training> getAssignedTrainings() {
        return attendedTrainings.values();
    }

    static private String fileName = "./entities.ser";

    static void save() {
        try (ObjectOutputStream aus = new ObjectOutputStream(new FileOutputStream(fileName))) {
            aus.writeObject(theClerks);
            aus.writeObject(Training.getTrainings());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    static void read() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            theClerks = (Map<String, Clerk>) in.readObject();
            Map<String, Training> theTrainings = (Map<String, Training>) in.readObject();
            Training.setTheTrainings(theTrainings);
        } catch (FileNotFoundException ex) {
            System.out.println("Savefile not found!");

            create("admin", "aaAA11&&aa", true);

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

}