package com.io;

import javax.swing.*;
import java.io.Console;
import java.io.IOError;
import java.util.IllegalFormatException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Interface between UserInput and the main application.
 */
public class Input {
    /**
     * Get a string input from the console.
     * @return The input from console
     */
    public static String readString() {
        String inputString;

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                inputString = scanner.nextLine();
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.err.println("Invalid string, Please try again");
            }
        }

        return inputString;
    }

    /**
     * Get an integer input from the console.
     * @return The input from the console
     */
    public static int readInt() {
        int inputInt;

        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                inputInt = scanner.nextInt();
                break;
            } catch (InputMismatchException | NumberFormatException e) {
                System.err.println("Invalid number, Please try again");
            }
        }

        return inputInt;
    }

    /**
     * Get a password input from the console or if a console instance can't be
     * found load swing component to securely read the password.
     * @param message The message for the swing component to render
     * @return The password input
     */
    public static String readPassword(String message) {
        char[] passwordArray;
        Console console = System.console();
        if (console == null) {
            System.err.println("Couldn't get Console instance, loading GUI.");
            JPasswordField passwordField = new JPasswordField();
            return JOptionPane.showConfirmDialog(
                    null,
                    passwordField,
                    message,
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION ?
                    new String(passwordField.getPassword()) : "";
        }

        while (true) {
            try {
                passwordArray = console.readPassword("Enter your password...");
                break;
            } catch (IllegalFormatException e) {
                System.err.println("Password contained illegal syntax");
            } catch (IOError e) {
                System.err.println("Error occurred with input");
            }
        }

        return new String(passwordArray);
    }
}
