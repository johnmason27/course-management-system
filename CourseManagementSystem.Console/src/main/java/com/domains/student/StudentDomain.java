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

public class StudentDomain extends LoginDomain {
    private final CourseLoader courseLoader;
    private final InstructorLoader instructorLoader;
    private final StudentEditor studentEditor;

    public StudentDomain(AdminLoader adminLoader, StudentLoader studentLoader, InstructorLoader instructorLoader, CourseLoader courseLoader, StudentEditor studentEditor, AdminEditor adminEditor, InstructorEditor instructorEditor, CourseEditor courseEditor) {
        super(adminLoader, studentLoader, instructorLoader, courseLoader, studentEditor, adminEditor, instructorEditor, courseEditor);

        this.courseLoader = courseLoader;
        this.instructorLoader = instructorLoader;
        this.studentEditor = studentEditor;
    }

    @Override
    public void load() {
        Student currentUser = Session.getStudent();
        System.out.printf("Welcome back %s%n", currentUser.getUsername());

        while (true) {
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
