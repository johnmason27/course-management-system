package com.models;

import com.models.interfaces.IUser;

import java.util.UUID;

/**
 * Base user class which Admin, Student and Instructor extend.
 */
public class User implements IUser {
    private UUID id;
    private UserType userType;
    private String forename;
    private String surname;
    private String email;
    private String username;
    private String password;

    /**
     * Initialize a User.
     * @param id User id
     * @param userType User type
     * @param forename User forename
     * @param surname User surname
     * @param email User email
     * @param username User username
     * @param password User password
     */
    public User(UUID id, UserType userType, String forename, String surname, String email, String username, String password) {
        this.id = id;
        this.userType = userType;
        this.forename = forename;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
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
