package com.models;

import com.models.enums.UserType;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Users {
    private final ArrayList<User> users;
    
    public Users() {
        this.users = new ArrayList<>();
    }

    public void processRawUsers(ArrayList<String> users) {
        for (String userLine :
                users) {
            try {
                String[] parts = userLine.split(",");
                UserType userType;

                if (Objects.equals(parts[0], "Student")) {
                    userType = UserType.Student;
                } else if (Objects.equals(parts[0], "CourseAdmin")) {
                    userType = UserType.CourseAdmin;
                } else if (Objects.equals(parts[0], "Instructor")) {
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

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public ArrayList<String> getStringUsers() {
        ArrayList<String> stringUsers = new ArrayList<String>();

        for (User user :
                this.users) {
            String userType = user.getUserType().toString();
            String forename = user.getForename();
            String surname = user.getSurname();
            String email = user.getEmail();
            String username = user.getUsername();
            String password = user.getPassword();

            String line = String.format("%s,%s,%s,%s,%s,%s", userType, forename, surname, email, username, password);

            stringUsers.add(line);
        }

        return stringUsers;
    }

    public void updateUser(User user) {
        int index = 0;
        String username = user.getUsername();

        for (int i = 0; i < this.users.size(); i ++) {
            if (Objects.equals(this.users.get(i).getUsername(), username)) {
                index = i;
                break;
            }
        }

        this.users.set(index, user);
    }

    public User findUser(String usernameEmail) {
        return this.users.stream()
                .filter(user -> usernameEmail.equals(user.getUsername()) || usernameEmail.equals(user.getEmail()))
                .findAny()
                .orElse(null);
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void saveUsers() {
        // do all the logic in here
        // have a static users
        // convert users on construction
        // users.Add()
    }
}
