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

/**
 * Houses the ForgotPasswordDomain.
 */
public class ForgotPasswordDomain {
    private final StudentLoader studentLoader;
    private final InstructorLoader instructorLoader;
    private final AdminLoader adminLoader;
    private final StudentEditor studentEditor;
    private final InstructorEditor instructorEditor;
    private final AdminEditor adminEditor;

    /**
     * Initialize the ForgotPasswordDomain.
     * @param studentLoader Load students
     * @param instructorLoader Load instructors
     * @param adminLoader Load admins
     * @param studentEditor Edit students
     * @param instructorEditor Edit instructors
     * @param adminEditor Edit admins
     */
    public ForgotPasswordDomain(StudentLoader studentLoader, InstructorLoader instructorLoader, AdminLoader adminLoader, StudentEditor studentEditor, InstructorEditor instructorEditor, AdminEditor adminEditor) {
        this.studentLoader = studentLoader;
        this.instructorLoader = instructorLoader;
        this.adminLoader = adminLoader;
        this.studentEditor = studentEditor;
        this.instructorEditor = instructorEditor;
        this.adminEditor = adminEditor;
    }

    /**
     * Load the ForgotPasswordDomain.
     */
    public void load() {
        System.out.println("Forgot Password Form!");
        // Find existing user
        while (true) {
            System.out.println("Enter username or email:");
            String usernameEmail = Input.readString();
            Student student = this.studentLoader.find(usernameEmail);
            Instructor instructor = this.instructorLoader.find(usernameEmail);
            Admin admin = this.adminLoader.find(usernameEmail);

            // Reset password for user depending on type
            if (student != null) {
                System.out.println("Found user.");
                String hashedPassword = this.getNewPassword();
                student.setPassword(hashedPassword);
                this.studentEditor.update(student);
                System.out.printf("User %s password has been reset.%n", student.getUsername());
                break;
            } else if (instructor != null) {
                System.out.println("Found user.");
                String hashedPassword = this.getNewPassword();
                instructor.setPassword(hashedPassword);
                this.instructorEditor.update(instructor);
                System.out.printf("User %s password has been reset.%n", instructor.getUsername());
                break;
            } else if (admin != null) {
                System.out.println("Found user.");
                String hashedPassword = this.getNewPassword();
                admin.setPassword(hashedPassword);
                this.adminEditor.update(admin);
                System.out.printf("User %s password has been reset.%n", admin.getUsername());
                break;
            } else {
                System.err.println("User doesn't exist with that username or email, enter another.");
            }
        }
    }

    private String getNewPassword() {
        String password;
        String confirmPassword;
        // Get new password
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
        // Confirm new password
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

        // Hash new password and return
        try {
            return StringHash.hash(password);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Unable to hash password. Closing program.");
            System.exit(1);
            return null;
        }
    }
}
