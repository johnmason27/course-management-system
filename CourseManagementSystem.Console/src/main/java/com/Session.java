package com;

import com.models.Admin;
import com.models.Instructor;
import com.models.Student;

/**
 * Cache of the current users logged into the system.
 */
public class Session {
    public static Student student;
    public static Instructor instructor;
    public static Admin admin;

    /**
     * Get the cache of the current student.
     * @return The current student
     */
    public static Student getStudent() {
        return student;
    }

    /**
     * Set the cache of the current student.
     */
    public static void setStudent(Student currentStudent) {
        student = currentStudent;
    }

    /**
     * Get the cache of the current instructor.
     * @return The current instructor
     */
    public static Instructor getInstructor() {
        return instructor;
    }

    /**
     * Set the cache of the current instructor.
     */
    public static void setInstructor(Instructor currentInstructor) {
        instructor = currentInstructor;
    }

    /**
     * Get the cache of the current admin.
     * @return The current admin
     */
    public static Admin getAdmin() {
        return admin;
    }

    /**
     * Set the cache of the current admin.
     */
    public static void setAdmin(Admin currentAdmin) {
        admin = currentAdmin;
    }
}
