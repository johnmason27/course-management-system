package com.models;

import com.consts.FileNames;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.io.IOManager;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

public class Users {
    private static IOManager ioManager;
    private static ArrayList<User> users;

    public Users() {
        ioManager = new IOManager();
        String usersFileContents = ioManager.readFile(FileNames.Users);
        Gson gson = new Gson();
        Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
        users = gson.fromJson(usersFileContents, userListType);
    }

    public static void addUser(User user) {
        users.add(user);
        saveUsers();
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
        saveUsers();
    }

    public static User findUser(String usernameEmail) {
        return users.stream()
                .filter(user -> usernameEmail.equals(user.getUsername()) || usernameEmail.equals(user.getEmail()))
                .findAny()
                .orElse(null);
    }

    private static void saveUsers() {
        Gson gson = new Gson();
        String usersJson = gson.toJson(Users.users);
        ioManager.writeFile(FileNames.Users, usersJson);
    }
}
