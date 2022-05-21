package com.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private UUID userId;
    private User user;

    @BeforeEach
    public void setup() {
        this.userId = UUID.randomUUID();
        this.user = new User(userId, UserType.Student, "John", "Mason", "john.mason@wlv.ac.uk", "john.mason", "password");
    }

    @Test
    public void testItConstructsTheUserAndPopulatesTheUser() {
        assertEquals(this.userId, this.user.getId());
        assertEquals(UserType.Student, this.user.getUserType());
        assertEquals("John", this.user.getForename());
        assertEquals("Mason", this.user.getSurname());
        assertEquals("john.mason@wlv.ac.uk", this.user.getEmail());
        assertEquals("john.mason", this.user.getUsername());
        assertEquals("password", this.user.getPassword());
    }

    @Test
    public void testSetIdSetsTheId() {
        UUID newId = UUID.randomUUID();
        this.user.setId(newId);
        assertEquals(newId, this.user.getId());
    }

    @Test
    public void testSetUserTypeSetsTheUserType() {
        UserType newUserType = UserType.Admin;
        this.user.setUserType(newUserType);
        assertEquals(newUserType, this.user.getUserType());
    }

    @Test
    public void testSetForenameSetsTheForename() {
        String newForename = "Bill";
        this.user.setForename(newForename);
        assertEquals(newForename, "Bill");
    }

    @Test
    public void testSetSurnameSetsTheSurname() {
        String newSurname = "Bob";
        this.user.setSurname(newSurname);
        assertEquals(newSurname, this.user.getSurname());
    }

    @Test
    public void testSetEmailSetsTheEmail() {
        String newEmail = "john.mason.new@wlv.ac.uk";
        this.user.setEmail(newEmail);
        assertEquals(newEmail, this.user.getEmail());
    }

    @Test
    public void testSetUsernameSetsTheUsername() {
        String newUsername = "john.mason.new";
        this.user.setUsername(newUsername);
        assertEquals(newUsername, this.user.getUsername());
    }

    @Test
    public void testSetPasswordSetsThePassword() {
        String newPassword = "passwordNew";
        this.user.setPassword(newPassword);
        assertEquals(newPassword, this.user.getPassword());
    }
}
