package com.printers;

import com.models.Course;
import com.models.Module;
import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;

public class CoursePrinter {
    public static void printCourse(Course course) {
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("Id", "Name", "Availability");
        table.addRule();
        table.addRow(course.getId(), course.getName(), course.getAvailability() ? "Available" : "Unavailable");
        table.addRule();

        System.out.println(table.render());
    }

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
