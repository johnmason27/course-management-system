package com;

import com.models.Admin;
import com.models.Instructor;
import com.models.Student;

public class Session {
    public static Student student;
    public static Instructor instructor;
    public static Admin admin;

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

    public static Admin getAdmin() {
        return admin;
    }

    public static void setAdmin(Admin currentAdmin) {
        admin = currentAdmin;
    }
}
