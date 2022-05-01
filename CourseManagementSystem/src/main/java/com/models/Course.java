package com.models;

import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private UUID id;
    private String courseName;
    private ArrayList<Module> courseModules;
    private boolean isCourseRunning;

    public Course(UUID id, String courseName, ArrayList<Module> courseModules, boolean isCourseRunning) {
        this.id = id;
        this.courseName = courseName;
        this.courseModules = courseModules;
        this.isCourseRunning = isCourseRunning;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ArrayList<Module> getCourseModules() {
        return this.courseModules;
    }

    public void setCourseModules(ArrayList<Module> courseModules) {
        this.courseModules = courseModules;
    }

    public void addCourseModule(Module module) {
        this.courseModules.add(module);
    }

    public Module findModule(Module module) {
        return this.courseModules.stream()
                .filter(m -> module.getModuleName().equals(m.getModuleName()))
                .findAny()
                .orElse(null);
    }

    public boolean getIsCourseRunning() {
        return this.isCourseRunning;
    }

    public void setIsCourseRunning(boolean isCourseRunning) {
        this.isCourseRunning = isCourseRunning;
    }
}
