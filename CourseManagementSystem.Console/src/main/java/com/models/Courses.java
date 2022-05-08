package com.models;

import com.consts.FileNames;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.io.IOManager;

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

    private static void saveCourses() {
        Gson gson = new Gson();
        String coursesJson = gson.toJson(courses);
        ioManager.writeFile(FileNames.Courses, coursesJson);
    }
}
