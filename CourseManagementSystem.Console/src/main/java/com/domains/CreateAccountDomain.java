package com.domains;

import com.io.Input;
import com.models.User;
import com.models.UserType;
import com.models.Users;
import com.security.StringHash;
import com.security.StringValidator;
import de.vandermeer.asciitable.AsciiTable;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class CreateAccountDomain {
    public static void load() {
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
            for (String option :
                    userTypeOptions) {
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
                userType = UserType.CourseAdmin;
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

            if (username.length() == 0) {
                System.err.println("Username cannot be blank.");
            } else if (username.contains(" ")) {
                System.err.println("Username cannot contain whitespace.");
            } else if (Users.findUser(username) != null) {
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

            if (email.length() == 0) {
                System.err.println("Email cannot be blank.");
            } else if (email.contains(" ")) {
                System.err.println("Email cannot contain whitespace.");
            } else if (!StringValidator.isValidEmail(email)) {
                System.err.println("Email entered is invalid, please enter another.");
            } else if (Users.findUser(email) != null) {
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

            for (String option :
                    options) {
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
                User newUser = new User(id, userType, forename, surname, email, username, hashedPassword, null);
                Users.addUser(newUser);

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
