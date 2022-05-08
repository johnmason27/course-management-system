package com.models;

import com.consts.FileNames;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.io.IOManager;
import de.vandermeer.asciitable.AsciiTable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.UUID;

public class Courses {
    private static IOManager ioManager;
    private static ArrayList<Course> courses;

    public Courses() {
        ioManager = new IOManager();
        String coursesFileContents = ioManager.readFile(FileNames.Courses);
        Gson gson = new Gson();
        Type courseListType = new TypeToken<ArrayList<Course>>(){}.getType();
        courses = gson.fromJson(coursesFileContents, courseListType);
    }

    public static void addCourse(Course course) {
        courses.add(course);
        saveCourses();
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static ArrayList<Course> getAvailableCourses() {
        ArrayList<Course> availableCourses = new ArrayList<>();

        for (Course course :
                courses) {
            if (course.getAvailability()) {
                availableCourses.add(course);
            }
        }

        return availableCourses;
    }

    public static void updateCourse(Course course) {
        int index = 0;
        String courseName = course.getName();

        for (int i = 0; i < courses.size(); i ++) {
            if (courses.get(i).getName().equals(courseName)) {
                index = i;
                break;
            }
        }

        courses.set(index, course);
        saveCourses();
    }

    public static void updateCourseById(Course course) {
        int index = 0;
        UUID courseId = course.getId();

        for (int i = 0; i < courses.size(); i ++) {
            if (courses.get(i).getId().equals(courseId)) {
                index = i;
                break;
            }
        }

        courses.set(index, course);
        saveCourses();
    }

    public static void deleteCourse(Course course) {
        courses.remove(course);
        saveCourses();
    }

    public static Course findCourse(String courseName) {
        return courses.stream()
                .filter(course -> courseName.equals(course.getName()))
                .findAny()
                .orElse(null);
    }

    public static void printCourses(ArrayList<Course> courses) {
        if (courses.size() == 0) {
            System.out.println("Oh looks like there is no existing courses.");
            return;
        }

        AsciiTable coursesTable = new AsciiTable();
        coursesTable.addRule();
        coursesTable.addRow("Id", "Name", "Availability");
        coursesTable.addRule();

        for (Course course :
                courses) {
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

        for (Module module :
                modules) {
            modulesTable.addRow(module.getId(), module.getName(), module.getLevel(), module.getAvailability() ? "Available" : "Unavailable");
            modulesTable.addRule();
        }

        System.out.println(modulesTable.render());
    }

    private static void saveCourses() {
        Gson gson = new Gson();
        String coursesJson = gson.toJson(courses);
        ioManager.writeFile(FileNames.Courses, coursesJson);
    }
}
