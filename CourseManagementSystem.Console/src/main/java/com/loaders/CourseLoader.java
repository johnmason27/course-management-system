package com.loaders;

import com.consts.FileNames;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.io.IOManager;
import com.models.Course;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.UUID;

public class CourseLoader implements ILoader<Course> {
    private final IOManager ioManager = new IOManager();
    private final Gson gson = new Gson();

    public ArrayList<Course> loadAll() {
        String coursesFileContents = this.ioManager.readFile(FileNames.COURSES);
        Type courseListType = new TypeToken<ArrayList<Course>>(){}.getType();
        ArrayList<Course> courses = this.gson.fromJson(coursesFileContents, courseListType);

        if (courses == null) {
            return new ArrayList<>();
        }

        return courses;
    }

    public ArrayList<Course> findAvailable() {
        ArrayList<Course> availableCourses = new ArrayList<>();

        for (Course c : this.loadAll()) {
            if (c.getAvailability()) {
                availableCourses.add(c);
            }
        }

        return availableCourses;
    }

    public Course find(String name) {
        return this.loadAll().stream()
                .filter(c -> c.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    public Course find(UUID id) {
        return this.loadAll().stream()
                .filter(c -> c.getId().equals(id))
                .findAny()
                .orElse(null);
    }
}
