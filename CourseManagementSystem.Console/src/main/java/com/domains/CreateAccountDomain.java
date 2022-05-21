package com.domains;

import com.editors.AdminEditor;
import com.editors.InstructorEditor;
import com.editors.StudentEditor;
import com.io.Input;
import com.loaders.AdminLoader;
import com.loaders.InstructorLoader;
import com.loaders.StudentLoader;
import com.models.*;
import com.security.StringHash;
import com.security.StringValidator;
import de.vandermeer.asciitable.AsciiTable;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;

public class CreateAccountDomain {
    private final StudentLoader studentLoader;
    private final InstructorLoader instructorLoader;
    private final AdminLoader adminLoader;
    private final StudentEditor studentEditor;
    private final InstructorEditor instructorEditor;
    private final AdminEditor adminEditor;

    public CreateAccountDomain(StudentLoader studentLoader, InstructorLoader instructorLoader, AdminLoader adminLoader, StudentEditor studentEditor, InstructorEditor instructorEditor, AdminEditor adminEditor) {
        this.studentLoader = studentLoader;
        this.instructorLoader = instructorLoader;
        this.adminLoader = adminLoader;
        this.studentEditor = studentEditor;
        this.instructorEditor = instructorEditor;
        this.adminEditor = adminEditor;
    }

    public void load() {
        System.out.println("Create Account Form!");
        System.out.println("Please fill in the following fields:");
        UserType userType;
        String forename;
        String surname;
        String username;
        String email;
        String password;
        String confirmPassword;

//      Get UserType
        String[] userTypeOptions = {
                "1 - Student",
                "2 - Instructor",
                "3 - Course Administrator"
        };

        while (true) {
            System.out.println("What type of account are you creating?");
            for (String option : userTypeOptions) {
                System.out.println(option);
            }

            int userTypeInput = Input.readInt();

            if (userTypeInput == 1) {
                userType = UserType.Student;
                break;
            } else if (userTypeInput == 2) {
                userType = UserType.Instructor;
                break;
            } else if (userTypeInput == 3) {
                userType = UserType.Admin;
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }

        System.out.println("User type set.");
//      Get Forename
        while (true) {
            System.out.println("Forename:");
            forename = Input.readString();

            if (forename.length() == 0) {
                System.err.println("Forename cannot be blank.");
            } else if (forename.contains(" ")) {
                System.err.println("Forename cannot contain whitespace.");
            } else {
                System.out.println("Forename set.");
                break;
            }
        }
//      Get Surname
        while (true) {
            System.out.println("Surname:");
            surname = Input.readString();

            if (surname.length() == 0) {
                System.err.println("Surname cannot be blank.");
            } else if (surname.contains(" ")) {
                System.err.println("Forename cannot contain whitespace.");
            } else {
                System.out.println("Surname set.");
                break;
            }
        }
//      Get Username
        while (true) {
            System.out.println("Username:");
            username = Input.readString();

            User user;
            if (userType == UserType.Student) {
                user = this.studentLoader.find(username);
            } else if (userType == UserType.Instructor) {
                user = this.instructorLoader.find(username);
            } else {
                user = this.adminLoader.find(username);
            }

            if (username.length() == 0) {
                System.err.println("Username cannot be blank.");
            } else if (username.contains(" ")) {
                System.err.println("Username cannot contain whitespace.");
            } else if (user != null) {
                System.err.println("Username already taken, please enter another.");
            } else {
                System.out.println("Username set.");
                break;
            }
        }
//      Get Email
        while (true) {
            System.out.println("Email:");
            email = Input.readString();

            User user;
            if (userType == UserType.Student) {
                user = this.studentLoader.find(email);
            } else if (userType == UserType.Instructor) {
                user = this.instructorLoader.find(email);
            } else {
                user = this.adminLoader.find(email);
            }

            if (email.length() == 0) {
                System.err.println("Email cannot be blank.");
            } else if (email.contains(" ")) {
                System.err.println("Email cannot contain whitespace.");
            } else if (!StringValidator.isValidEmail(email)) {
                System.err.println("Email entered is invalid, please enter another.");
            } else if (user != null) {
                System.err.println("Account already exists with this email, please enter another.");
            } else {
                System.out.println("Email set.");
                break;
            }
        }
//      Get Password
        while (true) {
            System.out.println("Password:");
            password = Input.readPassword("Enter Password");

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
//      Confirm Password
        while (true) {
            System.out.println("Confirm Password:");
            confirmPassword = Input.readPassword("Confirm Password");

            if (!confirmPassword.equals(password)) {
                System.err.println("Passwords do not match, enter your password again.");
            } else {
                System.out.println("Password set.");
                break;
            }
        }
//      Confirm Account Creation
        while (true) {
            String[] options = {
              "1 - Yes",
              "2 - No"
            };
            System.out.println("Are you sure you want to create your account?");

            for (String option : options) {
                System.out.println(option);
            }

            int option = Input.readInt();

            if (option == 1) {
                String hashedPassword = null;
            
                try {
                    hashedPassword = StringHash.hash(password);
                } catch (NoSuchAlgorithmException e) {
                    System.err.println("Unable to hash password. Closing program.");
                    System.exit(1);
                }

                UUID id = UUID.randomUUID();
                if (userType == UserType.Student) {
                    Student newStudent = new Student(id, userType, forename, surname, email, username, hashedPassword, null, 4, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                    this.studentEditor.add(newStudent);
                } else if (userType == UserType.Instructor) {
                    Instructor newInstructor = new Instructor(id, userType, forename, surname, email, username, hashedPassword, new ArrayList<>());
                    this.instructorEditor.add(newInstructor);
                } else {
                    Admin newAdmin = new Admin(id, userType, forename, surname, email, username, hashedPassword);
                    this.adminEditor.add(newAdmin);
                }

                System.out.println("Account created.");
                AsciiTable consoleTable = new AsciiTable();
                consoleTable.addRule();
                consoleTable.addRow("User type", "Forename", "Surname", "Email", "Username");
                consoleTable.addRule();
                consoleTable.addRow(userType, forename, surname, email, username);
                consoleTable.addRule();
                System.out.println(consoleTable.render());
                break;
            } else if (option == 2) {
                System.out.println("Going back to main screen.");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
    }
}
