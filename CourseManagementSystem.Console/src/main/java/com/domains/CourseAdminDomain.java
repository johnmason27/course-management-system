package com.domains;

import com.Session;
import com.io.Input;
import com.models.User;
import org.apache.commons.lang3.NotImplementedException;

public class CourseAdminDomain {
    public static void load() {
        User currentUser = Session.getUser();
        System.out.printf("Welcome back %s%n", currentUser.getUsername());

        String[] options = {
                "1 - Manage Courses",
                "2 - Print Student Report",
                "3 - Logout"
        };

        while (true) {
            for (String option :
                    options) {
                System.out.println(option);
            }
            System.out.println("What would you like to do?");
            int option = Input.readInt();

            if (option == 1) {
                CourseManagementDomain.load();
            } else if (option == 2) {
                throw new NotImplementedException("Print Student Report not programmed yet.");
            } else if (option == 3) {
                Session.setUser(null);
                System.out.println("Logged out!");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
    }
}
