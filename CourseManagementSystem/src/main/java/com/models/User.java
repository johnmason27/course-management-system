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

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getForename() {
        return this.forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

