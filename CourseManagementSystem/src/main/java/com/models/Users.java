package com.models;

import com.models.enums.UserType;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Users {
    private ArrayList<User> users;
    
    public Users() {
        this.users = new ArrayList<>();
    }

    public void processRawUsers(ArrayList<String> users) {
        for (String userLine :
                users) {
            try {
                String[] parts = userLine.split(",");
                UserType userType;

                if (Objects.equals(parts[0], "student")) {
                    userType = UserType.Student;
                } else if (Objects.equals(parts[0], "courseAdmin")) {
                    userType = UserType.CourseAdmin;
                } else if (Objects.equals(parts[0], "instructor")) {
                    userType = UserType.Instructor;
                } else {
                    throw new NoSuchElementException("User doesn't contain a userType.");
                }

                String forename = parts[1];
                String surname = parts[2];
                String email = parts[3];
                String username = parts[4];
                String password = parts[5];

                User user = new User(userType, forename, surname, email, username, password);
                this.users.add(user);
            } catch (NumberFormatException e) {
                String error = "Number conversion error in '" + userLine + "' - " + e.getMessage();
                System.out.println(error);
            } catch (ArrayIndexOutOfBoundsException e) {
                String error = "Not enough items in '" + userLine + "' index position: " + e.getMessage();
                System.out.println(error);
            }
        }
    }

    public User findUser(String usernameEmail) {
        return this.users.stream()
                .filter(user -> usernameEmail.equals(user.getUsername()) || usernameEmail.equals(user.getEmail()))
                .findAny()
                .orElse(null);
    }
}
