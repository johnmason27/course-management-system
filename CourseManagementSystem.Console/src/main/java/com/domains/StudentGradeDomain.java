package com.domains;

import com.Session;
import com.models.Student;
import com.printers.StudentPrinter;

public class StudentGradeDomain {
    public static void load() {
        Student loggedInUser = Session.getStudent();
        System.out.println("Here are your grades:");
        StudentPrinter.printGrades(loggedInUser);
    }
}
