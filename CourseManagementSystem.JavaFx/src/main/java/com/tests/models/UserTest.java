package com.tests.models;

import com.models.User;
import com.models.enums.UserType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class UserTest {
    private UUID userId;
    private User user;

    @Before
    public void setup() {
        this.userId = UUID.randomUUID();
        this.user = new User(userId, UserType.Student, "John", "Mason", "john.mason@wlv.ac.uk", "john.mason", "password");
    }

    @Test
    public void testItConstructsTheUserAndPopulatesTheUser() {
        User user = new User(userId, UserType.Student, "John", "Mason", "john.mason@wlv.ac.uk", "john.mason", "password");

        Assert.assertEquals(this.userId, user.getId());
        Assert.assertEquals(UserType.Student, user.getUserType());
        Assert.assertEquals("John", user.getForename());
        Assert.assertEquals("Mason", user.getSurname());
        Assert.assertEquals("john.mason@wlv.ac.uk", user.getEmail());
        Assert.assertEquals("john.mason", user.getUsername());
        Assert.assertEquals("password", user.getPassword());
    }

    @Test
    public void testSetIdSetsTheId() {
        UUID newId = UUID.randomUUID();
        this.user.setId(newId);
        Assert.assertEquals(newId, this.user.getId());
    }

    @Test
    public void testSetUserTypeSetsTheUserType() {
        UserType newUserType = UserType.CourseAdmin;
        this.user.setUserType(newUserType);
        Assert.assertEquals(newUserType, this.user.getUserType());
    }

    @Test
    public void testSetForenameSetsTheForename() {
        String newForename = "Bill";
        this.user.setForename(newForename);
        Assert.assertEquals(newForename, "Bill");
    }

    @Test
    public void testSetSurnameSetsTheSurname() {
        String newSurname = "Bob";
        this.user.setSurname(newSurname);
        Assert.assertEquals(newSurname, this.user.getSurname());
    }

    @Test
    public void testSetEmailSetsTheEmail() {
        String newEmail = "john.mason.new@wlv.ac.uk";
        this.user.setEmail(newEmail);
        Assert.assertEquals(newEmail, this.user.getEmail());
    }

    @Test
    public void testSetUsernameSetsTheUsername() {
        String newUsername = "john.mason.new";
        this.user.setUsername(newUsername);
        Assert.assertEquals(newUsername, this.user.getUsername());
    }

    @Test
    public void testSetPasswordSetsThePassword() {
        String newPassword = "passwordNew";
        this.user.setPassword(newPassword);
        Assert.assertEquals(newPassword, this.user.getPassword());
    }
}
