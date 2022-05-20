package com.domains;

import com.Session;
import com.io.Input;
import com.models.Admin;

public class AdminDomain {
    public static void load() {
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
                CourseManagementDomain.load();
            } else if (option == 2) {
                InstructorManagementDomain.load();
            } else if (option == 3) {
                StudentManagementDomain.load();
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
