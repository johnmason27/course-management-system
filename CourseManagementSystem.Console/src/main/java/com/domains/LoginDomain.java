package com.domains;

import com.Session;
import com.io.Input;
import com.loaders.AdminLoader;
import com.loaders.InstructorLoader;
import com.loaders.StudentLoader;
import com.models.*;
import com.security.StringHash;

import java.security.NoSuchAlgorithmException;

public class LoginDomain {
    private static final StudentLoader studentLoader = new StudentLoader();
    private static final InstructorLoader instructorLoader = new InstructorLoader();
    private static final AdminLoader adminLoader = new AdminLoader();

    public static void load() {
        System.out.println("Login Form!");
//      Get username or email
        while (true) {
            System.out.println("Enter username or email:");
            String usernameEmail = Input.readString();
            Student student = studentLoader.find(usernameEmail);
            Instructor instructor = instructorLoader.find(usernameEmail);
            Admin admin = adminLoader.find(usernameEmail);

            if (student != null) {
                System.out.println("Found user.");
//              Get Password
                checkPassword(student);
//              Login and load next domain
                System.out.println("Password matches! Logged in.");
                Session.setStudent(student);
                StudentDomain.load();
                break;
            } else if (instructor != null) {
                System.out.println("Found user.");
//              Get Password
                checkPassword(instructor);
//              Login and load next domain
                System.out.println("Password matches! Logged in.");
                Session.setInstructor(instructor);
                InstructorDomain.load();
                break;
            } else if (admin != null) {
                System.out.println("Found user.");
//              Get Password
                checkPassword(admin);
//              Login and load next domain
                System.out.println("Password matches! Logged in.");
                Session.setAdmin(admin);
                AdminDomain.load();
                break;
            } else {
                System.err.println("User doesn't exist with that username or email, enter another.");
            }
        }
    }

    private static void checkPassword(User user) {
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

            if (!user.getPassword().equals(hashedPassword)) {
                System.err.println("Incorrect password, try again.");
            } else {
                break;
            }
        }
    }
}
