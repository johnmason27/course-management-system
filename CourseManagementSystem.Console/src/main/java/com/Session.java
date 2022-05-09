package com;

import com.models.Instructor;
import com.models.Student;
import com.models.User;

public class Session {
    public static User user;
    public static Student student;
    public static Instructor instructor;

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

    public static Instructor getInstructor() {
        return instructor;
    }

    public static void setInstructor(Instructor currentInstructor) {
        instructor = currentInstructor;
    }
}
