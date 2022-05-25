package com.domains.student;

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
import com.models.Student;

/**
 * Houses all the StudentDomain where students can view courses, enrolled courses,
 * enrolled modules and grades.
 */
public class StudentDomain extends LoginDomain {
    private final CourseLoader courseLoader;
    private final InstructorLoader instructorLoader;
    private final StudentEditor studentEditor;

    /**
     * Initialize the StudentDomain
     * @param adminLoader Load admins
     * @param studentLoader Load students
     * @param instructorLoader Load instructors
     * @param courseLoader Load courses
     * @param studentEditor Edit students
     * @param adminEditor Edit admins
     * @param instructorEditor Edit instructors
     * @param courseEditor Edit courses
     */
    public StudentDomain(AdminLoader adminLoader, StudentLoader studentLoader, InstructorLoader instructorLoader, CourseLoader courseLoader, StudentEditor studentEditor, AdminEditor adminEditor, InstructorEditor instructorEditor, CourseEditor courseEditor) {
        super(adminLoader, studentLoader, instructorLoader, courseLoader, studentEditor, adminEditor, instructorEditor, courseEditor);

        this.courseLoader = courseLoader;
        this.instructorLoader = instructorLoader;
        this.studentEditor = studentEditor;
    }

    /**
     * Load the StudentDomain.
     */
    @Override
    public void load() {
        Student currentUser = Session.getStudent();
        System.out.printf("Welcome back %s%n", currentUser.getUsername());

        while (true) {
            // Get which option to add to the options depending on if the student
            // is enrolled onto a course or not
            String option1;
            if (Session.student.getEnrolledCourseId() == null) {
                option1 = "1 - View course list";
            } else {
                option1 = "1 - View enrolled course";
            }
            String[] options = {
                    option1,
                    "2 - View grades",
                    "3 - Logout"
            };

            for (String option :
                    options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            // Load the next domain depending on the input
            if (option == 1) {
                if (Session.student.getEnrolledCourseId() == null) {
                    StudentCourseListDomain studentCourseListDomain = new StudentCourseListDomain(this.courseLoader, this.studentEditor);
                    studentCourseListDomain.load();
                } else {
                    StudentEnrolledDomain studentEnrolledDomain = new StudentEnrolledDomain(this.courseLoader, this.instructorLoader, this.studentEditor);
                    studentEnrolledDomain.load();
                }
            } else if (option == 2) {
                StudentGradeDomain studentGradeDomain = new StudentGradeDomain(this.courseLoader);
                studentGradeDomain.load();
            } else if (option == 3) {
                Session.setStudent(null);
                System.out.println("Logged out!");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
    }
}
