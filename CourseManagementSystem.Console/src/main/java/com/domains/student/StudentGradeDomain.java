package com.domains.student;

import com.Session;
import com.loaders.CourseLoader;
import com.models.Student;
import com.printers.StudentPrinter;

/**
 * Houses the StudentGradeDomain where students can view grades.
 */
public class StudentGradeDomain {
    private final CourseLoader courseLoader;

    /**
     * Initialize the StudentGradeDomain.
     * @param courseLoader Load courses
     */
    public StudentGradeDomain(CourseLoader courseLoader) {
        this.courseLoader = courseLoader;
    }

    /**
     * Load the StudentGradeDomain.
     */
    public void load() {
        Student loggedInUser = Session.getStudent();
        // Print the students completed modules with their grades
        System.out.println("Here are your grades:");
        StudentPrinter.printCompletedModulesWithGrade(loggedInUser.getCompletedModulesWithGrade(this.courseLoader));

        // If the student is level 7 congratulate them
        if (loggedInUser.getLevel() == 7) {
            System.out.println("You have received the correct amount of grades at levels 4, 5, 6 to graduate congratulations!");
        }
    }
}
