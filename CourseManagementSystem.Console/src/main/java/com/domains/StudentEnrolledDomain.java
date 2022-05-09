package com.domains;

import com.Session;
import com.io.Input;
import com.models.Course;
import com.models.Courses;
import com.models.Module;

import java.util.ArrayList;
import java.util.List;

public class StudentEnrolledDomain {
    public static void load() {
        System.out.println("Here you can view your enrolled course and it's modules.");
        Course enrolledCourse = Courses.findCourse(Session.student.getEnrolledCourseId());

        System.out.println("Your currently enrolled onto:");
        Course.printCourse(enrolledCourse);

        while (true) {
            String[] options = {
                    "1 - Yes",
                    "2 - No"
            };
            for (String option :
                    options) {
                System.out.println(option);
            }
            System.out.println("Would you like to view the modules on your course?");
            int option = Input.readInt();

            if (option == 1) {
                int studentLevel = Session.student.getLevel();
                System.out.printf("You are currently level %d, printing modules for your level or previous levels.%n", studentLevel);

                ArrayList<Module> courseModules = enrolledCourse.getModules();
                List<Module> availableModules = courseModules.stream()
                                .filter(m -> m.getLevel() <= studentLevel).toList();

                Courses.printModules(new ArrayList<>(availableModules));
                break;
            } else if (option == 2) {
                System.out.println("Going back...");
                break;
            } else {
                System.err.println("Enter a valid option.");
            }
        }
    }
}
