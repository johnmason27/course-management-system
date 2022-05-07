package com.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringValidator {
    private final static String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,64}$";
    private final static String EMAIL_REGEX = "^(.+)@(.+)$";

    public static boolean isValidPassword(String password) {
        if (password.contains(" ")) {
            return false;
        }

        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        if (email.contains(" ")) {
            return false;
        }

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
