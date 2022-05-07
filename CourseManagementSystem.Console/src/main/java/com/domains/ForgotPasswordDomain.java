package com.domains;

import com.io.Input;
import com.models.User;
import com.models.Users;
import com.security.StringHash;
import com.security.StringValidator;

import java.security.NoSuchAlgorithmException;

public class ForgotPasswordDomain {
    public static void load() {
        System.out.println("Forgot Password Form!");
        User userToReset;
        String password;
        String confirmPassword;
//      Find existing user
        while (true) {
            System.out.println("Enter username or email:");
            String usernameEmail = Input.readString();
            userToReset = Users.findUser(usernameEmail);

            if (userToReset == null) {
                System.err.println("User doesn't exist with that username or email, enter another.");
            } else {
                System.out.println("Found user.");
                break;
            }
        }
//      Get new password
        while (true) {
            System.out.println("Enter New Password:");
            password = Input.readPassword("Enter New Password");

            if (password.length() == 0) {
                System.err.println("Password cannot be blank.");
            } else if (password.contains(" ")) {
                System.err.println("Password cannot contain whitespace.");
            } else if (!StringValidator.isValidPassword(password)) {
                System.err.println("Password must contain a number, lowercase character, uppercase character, contain no whitespace and be between 8 and 64 characters long.");
            } else {
                break;
            }
        }
//      Confirm new password
        while (true) {
            System.out.println("Confirm Password:");
            confirmPassword = Input.readPassword("Confirm Password");

            if (!confirmPassword.equals(password)) {
                System.err.println("Passwords do not match, enter your password again.");
            } else {
                System.out.println("Passwords match!");
                break;
            }
        }
//      Reset password
        String hashedPassword = null;

        try {
            hashedPassword = StringHash.hash(password);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Unable to hash password. Closing program.");
            System.exit(1);
        }

        userToReset.setPassword(hashedPassword);
        Users.updateUser(userToReset);

        System.out.printf("User %s password has been reset.%n", userToReset.getUsername());
    }
}
