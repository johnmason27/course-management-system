package com.printers;

import com.models.Course;
import com.models.Module;
import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;

public class CoursePrinter {
    public static void printCourse(Course course) {
        AsciiTable coursesTable = new AsciiTable();

        coursesTable.addRule();
        coursesTable.addRow("Id", "Name", "Availability");
        coursesTable.addRule();
        coursesTable.addRow(course.getId(), course.getName(), course.getAvailability() ? "Available" : "Unavailable");
        coursesTable.addRule();

        System.out.println(coursesTable.render());
    }

    public static void printCourses(ArrayList<Course> courses) {
        if (courses.size() == 0) {
            System.out.println("Oh looks like there are no courses.");
            return;
        }

        AsciiTable coursesTable = new AsciiTable();
        coursesTable.addRule();
        coursesTable.addRow("Id", "Name", "Availability");
        coursesTable.addRule();

        for (Course course : courses) {
            coursesTable.addRow(course.getId(), course.getName(), course.getAvailability() ? "Available" : "Unavailable");
            coursesTable.addRule();
        }

        System.out.println(coursesTable.render());
    }

    public static void printModules(ArrayList<Module> modules) {
        if (modules.size() == 0) {
            System.out.println("There are no modules.");
            return;
        }

        AsciiTable modulesTable = new AsciiTable();
        modulesTable.addRule();
        modulesTable.addRow("Id", "Name", "Level", "Availability");
        modulesTable.addRule();

        for (Module module : modules) {
            modulesTable.addRow(module.getId(), module.getName(), module.getLevel(), module.getAvailability() ? "Available" : "Unavailable");
            modulesTable.addRule();
        }

        System.out.println(modulesTable.render());
    }
}
