package com.domains.student;

import com.Session;
import com.loaders.CourseLoader;
import com.models.Student;
import com.printers.StudentPrinter;

public class StudentGradeDomain {
    private final CourseLoader courseLoader;

    public StudentGradeDomain(CourseLoader courseLoader) {
        this.courseLoader = courseLoader;
    }

    public void load() {
        Student loggedInUser = Session.getStudent();
        System.out.println("Here are your grades:");
        StudentPrinter.printCompletedModulesWithGrade(loggedInUser.getCompletedModulesWithGrade(this.courseLoader));

        if (loggedInUser.getLevel() == 7) {
            System.out.println("You have received the correct amount of grades at levels 4, 5, 6 to graduate congratulations!");
        }
    }
}
