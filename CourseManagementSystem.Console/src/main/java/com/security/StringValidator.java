package com.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validate strings used in the application.
 */
public class StringValidator {
    private final static String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,64}$";
    private final static String EMAIL_REGEX = "^(.+)@(.+)$";

    /**
     * Validate if a given password is valid checking for whitespace and
     * a match against a password regex.
     * @param password The password to validate
     * @return The result of the validation
     */
    public static boolean isValidPassword(String password) {
        if (password.contains(" ")) {
            return false;
        }

        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * Validate if a given email is valid checking for whitespace and
     * a match against an email regex.
     * @param email The email to validate
     * @return The result of the validation
     */
    public static boolean isValidEmail(String email) {
        if (email.contains(" ")) {
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
