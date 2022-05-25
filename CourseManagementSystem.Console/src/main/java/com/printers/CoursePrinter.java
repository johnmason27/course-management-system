package com.printers;

import com.models.Course;
import com.models.Module;
import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;

/**
 * Print all the course information into ASCII tables.
 */
public class CoursePrinter {
    /**
     * Print a given course into an ASCII table.
     * @param course Course to print
     */
    public static void printCourse(Course course) {
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("Id", "Name", "Availability");
        table.addRule();
        table.addRow(course.getId(), course.getName(), course.getAvailability() ? "Available" : "Unavailable");
        table.addRule();

        System.out.println(table.render());
    }

    /**
     * Print all the given courses into an ASCII table.
     * @param courses Courses to print
     */
    public static void printCourses(ArrayList<Course> courses) {
        if (courses.size() == 0) {
            System.out.println("Oh looks like there are no courses.");
            return;
        }

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("Id", "Name", "Availability");
        table.addRule();

        for (Course c : courses) {
            table.addRow(c.getId(), c.getName(), c.getAvailability() ? "Available" : "Unavailable");
            table.addRule();
        }

        System.out.println(table.render());
    }

    /**
     * Print all the given modules into an ASCII table.
     * @param modules Modules to print
     */
    public static void printModules(ArrayList<Module> modules) {
        if (modules.size() == 0) {
            System.out.println("There are no modules.");
            return;
        }

        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow("Id", "Name", "Level", "Availability", "Optional");
        table.addRule();

        for (Module m : modules) {
            table.addRow(m.getId(), m.getName(), m.getLevel(), m.getAvailability() ? "Available" : "Unavailable", m.getOptional() ? "Optional" : "Required");
            table.addRule();
        }

        System.out.println(table.render());
    }
}
