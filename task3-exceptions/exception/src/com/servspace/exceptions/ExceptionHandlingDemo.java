package com.servspace.exceptions;

import java.util.Scanner;

public class ExceptionHandlingDemo {

    public static void registerUser(String username, boolean forceDbFail) {
        // check if empty or null
        if (username == null || username.trim().isEmpty()) {
            throw new InvalidInputException("Username cannot be empty!");
        }

        // check for numbers/special characters
        for (char c : username.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new InvalidInputException("Username must contain characters only!");
            }
        }
        //System.out.println("Username format is valid.");

        // fake db connection check
        System.out.println("Connecting to database...");
        if (forceDbFail) {
            throw new DatabaseConnectionException("Database server is down.");
        }

        System.out.println("SUCCESS: " + username + " registered successfully.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Test 1: testing input validation logic
        System.out.print("Test 1 - Enter Username (invalid input test): ");
        String user1 = sc.nextLine();
        try {
            registerUser(user1, false);
        } catch (InvalidInputException e) {
            System.out.println(" input error: " + e.getMessage());
        } catch (DatabaseConnectionException e) {
            System.out.println("database error: " + e.getMessage());
        }

        System.out.println();

        // Test 2: forcing db failure scenario
        System.out.print("Test 2 - Enter Username (db crash test): ");
        String user2 = sc.nextLine();
        try {
            registerUser(user2, true);
        } catch (InvalidInputException e) {
            System.out.println("input error: " + e.getMessage());
        } catch (DatabaseConnectionException e) {
            System.out.println("database                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              error: " + e.getMessage());
        }

        sc.close();
        System.out.println("\nProgram executed with no crashes.");
    }
}