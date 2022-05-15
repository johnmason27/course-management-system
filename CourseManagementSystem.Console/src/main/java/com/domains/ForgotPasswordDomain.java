package com.domains;

import com.editors.AdminEditor;
import com.editors.InstructorEditor;
import com.editors.StudentEditor;
import com.io.Input;
import com.loaders.AdminLoader;
import com.loaders.InstructorLoader;
import com.loaders.StudentLoader;
import com.models.Admin;
import com.models.Instructor;
import com.models.Student;
import com.security.StringHash;
import com.security.StringValidator;

import java.security.NoSuchAlgorithmException;

public class ForgotPasswordDomain {
    private static final StudentLoader studentLoader = new StudentLoader();
    private static final InstructorLoader instructorLoader = new InstructorLoader();
    private static final AdminLoader adminLoader = new AdminLoader();
    private static final StudentEditor studentEditor = new StudentEditor();
    private static final InstructorEditor instructorEditor = new InstructorEditor();
    private static final AdminEditor adminEditor = new AdminEditor();

    public static void load() {
        System.out.println("Forgot Password Form!");
//      Find existing user
        while (true) {
            System.out.println("Enter username or email:");
            String usernameEmail = Input.readString();
            Student student = studentLoader.find(usernameEmail);
            Instructor instructor = instructorLoader.find(usernameEmail);
            Admin admin = adminLoader.find(usernameEmail);

            if (student != null) {
//              Reset password
                System.out.println("Found user.");
                String hashedPassword = getNewPassword();
                student.setPassword(hashedPassword);
                studentEditor.update(student);
                System.out.printf("User %s password has been reset.%n", student.getUsername());
                break;
            } else if (instructor != null) {
//              Reset password
                System.out.println("Found user.");
                String hashedPassword = getNewPassword();
                instructor.setPassword(hashedPassword);
                instructorEditor.update(instructor);
                System.out.printf("User %s password has been reset.%n", instructor.getUsername());
                break;
            } else if (admin != null) {
//              Reset password
                System.out.println("Found user.");
                String hashedPassword = getNewPassword();
                admin.setPassword(hashedPassword);
                adminEditor.update(admin);
                System.out.printf("User %s password has been reset.%n", admin.getUsername());
                break;
            } else {
                System.err.println("User doesn't exist with that username or email, enter another.");
            }
        }
    }

    private static String getNewPassword() {
        String password;
        String confirmPassword;
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

        try {
            return StringHash.hash(password);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Unable to hash password. Closing program.");
            System.exit(1);
            return null;
        }
    }
}
