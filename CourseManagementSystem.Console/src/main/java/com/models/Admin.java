package com.models;

import com.models.interfaces.IAdmin;

import java.util.UUID;

/**
 * Admin user that managers courses, students and instructors.
 */
public class Admin extends User implements IAdmin {
    /**
     * Initialize an Admin.
     * @param id Admin id
     * @param userType Admin user type
     * @param forename Admin forename
     * @param surname Admin surname
     * @param email Admin email
     * @param username Admin username
     * @param password Admin password
     */
    public Admin(UUID id, UserType userType, String forename, String surname, String email, String username, String password) {
        super(id, userType, forename, surname, email, username, password);
    }
}
