package com.io;

import javax.swing.*;
import java.io.Console;
import java.io.IOError;
import java.util.IllegalFormatException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
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
