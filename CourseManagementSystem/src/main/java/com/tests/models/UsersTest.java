package com.tests.models;

import com.models.User;
import com.models.Users;
import com.models.enums.UserType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class UsersTest {
    private Users users;
    private ArrayList<String> rawUsersData;

    @Before
    public void setup() {
        this.rawUsersData = new ArrayList<>();
        this.rawUsersData.add("Student,John,Mason,john.mason@wlv.ac.uk,john.mason,password");
        this.rawUsersData.add("Student,Joshua,Talbot,john.talbot@wlv.ac.uk,josh.talbot,password");
        this.rawUsersData.add("CourseAdmin,Bill,Bo,bill.bo@wlv.ac.uk,bill.bo,password");
        this.rawUsersData.add("Instructor,Dave,Cox,dave.cox@wlv.ac.uk,dave.cox,password");
        this.users = new Users();
        this.users.processRawUsers(this.rawUsersData);
    }

    @Test
    public void testProcessRawUsersPopulatesTheUsersArray() {
        ArrayList<String> rawUsersData = new ArrayList<>();
        rawUsersData.add("Student,John,Mason,john.mason@wlv.ac.uk,john.mason,password");
        rawUsersData.add("Student,Joshua,Talbot,john.talbot@wlv.ac.uk,josh.talbot,password");
        rawUsersData.add("CourseAdmin,Bill,Bo,bill.bo@wlv.ac.uk,bill.bo,password");
        rawUsersData.add("Instructor,Dave,Cox,dave.cox@wlv.ac.uk,dave.cox,password");

        Users users = new Users();
        users.processRawUsers(this.rawUsersData);

        Assert.assertEquals(4, users.getUsers().size());
    }

    @Test
    public void testGetUsersReturnsTheUsers() {
        Assert.assertEquals(4, this.users.getUsers().size());
        Assert.assertEquals("John", this.users.getUsers().get(0).getForename());
        Assert.assertEquals(UserType.Student, this.users.getUsers().get(1).getUserType());
        Assert.assertEquals("password", this.users.getUsers().get(2).getPassword());
        Assert.assertEquals("dave.cox@wlv.ac.uk", this.users.getUsers().get(3).getEmail());
    }

    @Test
    public void testGetStringUsersReturnsTheStringsOfUsers() {
        ArrayList<String> usersData = this.users.getStringUsers();

        Assert.assertTrue(usersData.get(0).contains("John"));
        Assert.assertTrue(usersData.get(1).contains("Student"));
        Assert.assertTrue(usersData.get(2).contains("password"));
        Assert.assertTrue(usersData.get(3).contains("dave.cox@wlv.ac.uk"));
    }

    @Test
    public void testUpdateUserUpdatesTheSpecificUser() {
        User user = new User(UserType.Student, "John", "Mason", "john.mason@wlv.ac.uk", "john.mason", "password");
        String updatedPassword = "updatedPassword";
        user.setPassword(updatedPassword);
        this.users.updateUser(user);
        User updatedUser = this.users.findUser("john.mason");

        Assert.assertEquals(updatedPassword, updatedUser.getPassword());
    }

    @Test
    public void testFindUserReturnsAUserIfTheyExist() {
        User user = this.users.findUser("john.mason");

        Assert.assertEquals("John", user.getForename());
        Assert.assertEquals("Mason", user.getSurname());
        Assert.assertEquals("john.mason@wlv.ac.uk", user.getEmail());
    }

    @Test
    public void testFindUserReturnsNullIfTheyDoNotExist() {
        User user = this.users.findUser("bill");

        Assert.assertNull(user);
    }
}
