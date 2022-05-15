package com.domains;

import com.Session;
import com.io.Input;
import com.models.Student;
import org.apache.commons.lang3.NotImplementedException;

public class StudentDomain {
    public static void load() {
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
                    StudentCourseListDomain.load();
                } else {
                    StudentEnrolledDomain.load();
                }
            } else if (option == 2) {
                StudentGradeDomain.load();
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
