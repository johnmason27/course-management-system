package com.domains;

import com.Session;
import com.models.Student;
import com.printers.StudentPrinter;

public class StudentGradeDomain {
    public static void load() {
        Student loggedInUser = Session.getStudent();
        System.out.println("Here are your grades:");
        StudentPrinter.printCompletedModulesWithGrade(loggedInUser.getCompletedModulesWithGrade());

        if (loggedInUser.getLevel() == 7) {
            System.out.println("You have received the correct amount of grades at levels 4, 5, 6 to graduate congratulations!");
        }
    }
}
