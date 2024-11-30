package com.hfu.userInterfaces.trainingManagement.util;

import java.util.Scanner;

public class Entry {

    private static String _answer;

    public static String getAnswer(String question){
        Scanner scanner = new Scanner(System.in);
        System.out.print(question);
        scanner.nextLine();
        return _answer;
    }

}
