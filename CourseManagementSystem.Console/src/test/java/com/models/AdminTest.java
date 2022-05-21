package com.models;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminTest {
    @Test
    public void testItPopulatesTheParentClass() {
        UUID id = UUID.randomUUID();
        UserType userType = UserType.Admin;
        String forename = "John";
        String surname = "Mason";
        String email = "jjnrmason@me.com";
        String username = "john.mason";
        String password = "password";
        Admin admin = new Admin(id, userType, forename, surname, email, username, password);

        assertEquals(id, admin.getId());
        assertEquals(userType, admin.getUserType());
        assertEquals(forename, admin.getForename());
        assertEquals(surname, admin.getSurname());
        assertEquals(email, admin.getEmail());
        assertEquals(username, admin.getUsername());
        assertEquals(password, admin.getPassword());
    }
}
