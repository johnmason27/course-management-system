package com.domains;

import com.Session;
import com.domains.admin.AdminDomain;
import com.domains.instructor.InstructorDomain;
import com.domains.student.StudentDomain;
import com.editors.AdminEditor;
import com.editors.CourseEditor;
import com.editors.InstructorEditor;
import com.editors.StudentEditor;
import com.io.Input;
import com.loaders.AdminLoader;
import com.loaders.CourseLoader;
import com.loaders.InstructorLoader;
import com.loaders.StudentLoader;
import com.models.*;
import com.security.StringHash;

import java.security.NoSuchAlgorithmException;

public class LoginDomain {
    private final AdminLoader adminLoader;
    private final StudentLoader studentLoader;
    private final InstructorLoader instructorLoader;
    private final CourseLoader courseLoader;
    private final StudentEditor studentEditor;
    private final AdminEditor adminEditor;
    private final InstructorEditor instructorEditor;
    private final CourseEditor courseEditor;

    public LoginDomain(AdminLoader adminLoader, StudentLoader studentLoader, InstructorLoader instructorLoader, CourseLoader courseLoader, StudentEditor studentEditor, AdminEditor adminEditor, InstructorEditor instructorEditor, CourseEditor courseEditor) {
        this.adminLoader = adminLoader;
        this.studentLoader = studentLoader;
        this.instructorLoader = instructorLoader;
        this.courseLoader = courseLoader;
        this.studentEditor = studentEditor;
        this.adminEditor = adminEditor;
        this.instructorEditor = instructorEditor;
        this.courseEditor = courseEditor;
    }

    public void load() {
        System.out.println("Login Form!");
//      Get username or email
        while (true) {
            System.out.println("Enter username or email:");
            String usernameEmail = Input.readString();
            Student student = this.studentLoader.find(usernameEmail);
            Instructor instructor = this.instructorLoader.find(usernameEmail);
            Admin admin = this.adminLoader.find(usernameEmail);

            if (student != null) {
                System.out.println("Found user.");
//              Get Password
                this.checkPassword(student);
//              Login and load next domain
                System.out.println("Password matches! Logged in.");
                Session.setStudent(student);
                StudentDomain studentDomain = new StudentDomain(adminLoader, studentLoader, instructorLoader, courseLoader, studentEditor, adminEditor, instructorEditor, courseEditor);
                studentDomain.load();
                break;
            } else if (instructor != null) {
                System.out.println("Found user.");
//              Get Password
                this.checkPassword(instructor);
//              Login and load next domain
                System.out.println("Password matches! Logged in.");
                Session.setInstructor(instructor);
                InstructorDomain instructorDomain = new InstructorDomain(adminLoader, studentLoader, instructorLoader, courseLoader, studentEditor, adminEditor, instructorEditor, courseEditor);
                instructorDomain.load();
                break;
            } else if (admin != null) {
                System.out.println("Found user.");
//              Get Password
                this.checkPassword(admin);
//              Login and load next domain
                System.out.println("Password matches! Logged in.");
                Session.setAdmin(admin);
                AdminDomain adminDomain = new AdminDomain(adminLoader, studentLoader, instructorLoader, courseLoader, studentEditor, adminEditor, instructorEditor, courseEditor);
                adminDomain.load();
                break;
            } else {
                System.err.println("User doesn't exist with that username or email, enter another.");
            }
        }
    }

    private void checkPassword(User user) {
        while (true) {
            System.out.println("Enter Password:");
            String password = Input.readPassword("Enter Password");
            String hashedPassword = null;

            try {
                hashedPassword = StringHash.hash(password);
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Unable to hash password. Closing program.");
                System.exit(1);
            }

            if (!user.getPassword().equals(hashedPassword)) {
                System.err.println("Incorrect password, try again.");
            } else {
                break;
            }
        }
    }
}
