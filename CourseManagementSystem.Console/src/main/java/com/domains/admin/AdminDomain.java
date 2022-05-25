package com.domains.admin;

import com.Session;
import com.domains.LoginDomain;
import com.editors.AdminEditor;
import com.editors.CourseEditor;
import com.editors.InstructorEditor;
import com.editors.StudentEditor;
import com.io.Input;
import com.loaders.AdminLoader;
import com.loaders.CourseLoader;
import com.loaders.InstructorLoader;
import com.loaders.StudentLoader;
import com.models.Admin;

/**
 * Houses the AdminDomain where you can manage courses, instructors and students.
 */
public class AdminDomain extends LoginDomain {
    private final CourseLoader courseLoader;
    private final CourseEditor courseEditor;
    private final InstructorLoader instructorLoader;
    private final InstructorEditor instructorEditor;
    private final StudentLoader studentLoader;
    private final StudentEditor studentEditor;

    /**
     * Initialize the AdminDomain.
     * @param adminLoader Load admins
     * @param studentLoader Load students
     * @param instructorLoader Load instructors
     * @param courseLoader Load courses
     * @param studentEditor Edit students
     * @param adminEditor Edit admins
     * @param instructorEditor Edit instructors
     * @param courseEditor Edit courses
     */
    public AdminDomain(AdminLoader adminLoader, StudentLoader studentLoader, InstructorLoader instructorLoader, CourseLoader courseLoader, StudentEditor studentEditor, AdminEditor adminEditor, InstructorEditor instructorEditor, CourseEditor courseEditor) {
        super(adminLoader, studentLoader, instructorLoader, courseLoader, studentEditor, adminEditor, instructorEditor, courseEditor);

        this.courseLoader = courseLoader;
        this.courseEditor = courseEditor;
        this.instructorLoader = instructorLoader;
        this.instructorEditor = instructorEditor;
        this.studentLoader = studentLoader;
        this.studentEditor = studentEditor;
    }

    /**
     * Load the AdminDomain.
     */
    @Override
    public void load() {
        Admin currentUser = Session.getAdmin();
        System.out.printf("Welcome back %s%n", currentUser.getUsername());

        String[] options = {
                "1 - Manage Courses",
                "2 - Manage Instructors",
                "3 - Manage Students",
                "4 - Logout"
        };

        while (true) {
            for (String option : options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            if (option == 1) {
                CourseManagementDomain courseManagementDomain = new CourseManagementDomain(this.courseLoader, this.courseEditor);
                courseManagementDomain.load();
            } else if (option == 2) {
                InstructorManagementDomain instructorManagementDomain = new InstructorManagementDomain(this.instructorLoader, this.instructorEditor, this.courseLoader, this.courseEditor);
                instructorManagementDomain.load();
            } else if (option == 3) {
                StudentManagementDomain studentManagementDomain = new StudentManagementDomain(this.studentLoader, this.studentEditor, this.courseLoader);
                studentManagementDomain.load();
            } else if (option == 4) {
                Session.setAdmin(null);
                System.out.println("Logged out!");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
    }
}
