package com.models;

import com.models.enums.UserType;

public class User {
    private UserType userType;
    private String forename;
    private String surname;
    private String email;
    private String username;
    private String password;

    public User(UserType userType, String forename, String surname, String email, String username, String password) {
        this.userType = userType;
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public String getForename() {
        return this.forename;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}

