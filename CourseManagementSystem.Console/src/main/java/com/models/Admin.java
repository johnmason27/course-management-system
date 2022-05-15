package com.models;

import com.models.interfaces.IAdmin;

import java.util.UUID;

public class Admin extends User implements IAdmin {
    public Admin(UUID id, UserType userType, String forename, String surname, String email, String username, String password) {
        super(id, userType, forename, surname, email, username, password);
    }
}
