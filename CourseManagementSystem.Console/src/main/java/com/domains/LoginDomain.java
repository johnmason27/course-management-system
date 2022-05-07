package com.domains;

import com.Session;
import com.io.Input;
import com.models.User;
import com.models.UserType;
import com.models.Users;
import com.security.StringHash;

import java.security.NoSuchAlgorithmException;

public class LoginDomain {
    public static void load() {
        System.out.println("Login Form!");
        User userToLogin;
//      Get username or email
        while (true) {
            System.out.println("Enter username or email:");
            String usernameEmail = Input.readString();
            userToLogin = Users.findUser(usernameEmail);

            if (userToLogin == null) {
                System.err.println("User doesn't exist with that username or email, enter another.");
            } else {
                System.out.println("Found user.");
                break;
            }
        }
//      Get Password
        while (true) {
            System.out.println("Enter Password:");
            String password = Input.readPassword("Enter Password");
            String hashedPassword = null;

            try {
                hashedPassword = StringHash.hash(password);
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Unable to hash password. Closing program.");
                System.exit(1);
            }

            if (!userToLogin.getPassword().equals(hashedPassword)) {
                System.err.println("Incorrect password, try again.");
            } else {
                break;
            }
        }
//      Login and load next domain
        System.out.println("Password matches! Logged in.");
        Session.setUser(userToLogin);

        if (userToLogin.getUserType() == UserType.Student) {
            StudentDomain.load();
        } else if (userToLogin.getUserType() == UserType.Instructor) {
            InstructorDomain.load();
        } else if (userToLogin.getUserType() == UserType.CourseAdmin) {
            CourseAdminDomain.load();
        } else {
            System.err.println("User does not contain a valid user type.");
            System.exit(1);
        }
    }
}
