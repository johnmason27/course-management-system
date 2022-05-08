package com;

import com.models.Student;
import com.models.User;

public class Session {
    public static User user;
    public static Student student;

    public static User getUser() {
        return user;
    }

    public static void setUser(User currentUser) {
        user = currentUser;
    }

    public static Student getStudent() {
        return student;
    }

    public static void setStudent(Student currentStudent) {
        student = currentStudent;
    }
}
