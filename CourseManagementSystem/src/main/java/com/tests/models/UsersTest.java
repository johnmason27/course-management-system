package com.tests.models;

import com.models.User;
import com.models.Users;
import com.models.enums.UserType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsersTest {
    @Before
    public void setup() {
        new Users();
    }

    @Test
    public void initialisationPopulatesTheUsersArray() {
        Assert.assertNotNull(Users.getUsers());
    }

    @Test
    public void testUpdateUserUpdatesTheSpecificUser() {
        User user = new User(UserType.Student, "John", "Mason", "john.mason@wlv.ac.uk", "john.mason", "password");
        String updatedPassword = "updatedPassword";
        user.setPassword(updatedPassword);
        Users.updateUser(user);
        User updatedUser = Users.findUser("john.mason");

        Assert.assertEquals(updatedPassword, updatedUser.getPassword());
    }

    @Test
    public void testFindUserReturnsAUserIfTheyExist() {
        User user = Users.findUser("john.mason");

        Assert.assertEquals("John", user.getForename());
        Assert.assertEquals("Mason", user.getSurname());
        Assert.assertEquals("john.mason@wlv.ac.uk", user.getEmail());
    }

    @Test
    public void testFindUserReturnsNullIfTheyDoNotExist() {
        User user = Users.findUser("bill");

        Assert.assertNull(user);
    }
}
