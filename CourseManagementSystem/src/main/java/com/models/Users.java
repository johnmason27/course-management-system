package com.models;

import com.consts.FileNames;
import com.io.IOManager;
import com.models.enums.UserType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

public class Users {
    private static IOManager ioManager;
    private static ArrayList<User> users;
    
    public Users() {
        ioManager = new IOManager();
        users = new ArrayList<>();
        ArrayList<String> stringUsers = ioManager.readFile(FileNames.Users);
        Users.processRawUsers(stringUsers);
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void updateUser(User user) {
        int index = 0;
        String username = user.getUsername();

        for (int i = 0; i < users.size(); i ++) {
            if (Objects.equals(users.get(i).getUsername(), username)) {
                index = i;
                break;
            }
        }

        users.set(index, user);
    }

    public static User findUser(String usernameEmail) {
        return users.stream()
                .filter(user -> usernameEmail.equals(user.getUsername()) || usernameEmail.equals(user.getEmail()))
                .findAny()
                .orElse(null);
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void saveUsers() throws IOException {
        Users.ioManager.writeFile(Users.getStringUsers(), FileNames.Users);
    }

    private static void processRawUsers(ArrayList<String> users) {
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
                Users.users.add(user);
            } catch (NumberFormatException e) {
                String error = "Number conversion error in '" + userLine + "' - " + e.getMessage();
                System.out.println(error);
            } catch (ArrayIndexOutOfBoundsException e) {
                String error = "Not enough items in '" + userLine + "' index position: " + e.getMessage();
                System.out.println(error);
            }
        }
    }

    private static ArrayList<String> getStringUsers() {
        ArrayList<String> stringUsers = new ArrayList<>();

        for (User user :
                users) {
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
}
