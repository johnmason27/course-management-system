package com.models;

import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;
import java.util.UUID;

public class Student {
    private UUID id;
    private UUID enrolledCourseId;
    private int level;
    private ArrayList<UUID> enrolledModules;
    private ArrayList<UUID> completedModules;

    public Student(UUID id, UUID enrolledCourseId, int level, ArrayList<UUID> enrolledModules, ArrayList<UUID> completedModules) {
        this.id = id;
        this.enrolledCourseId = enrolledCourseId;
        this.level = level;
        this.enrolledModules = enrolledModules;
        this.completedModules = completedModules;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getEnrolledCourseId() {
        return this.enrolledCourseId;
    }

    public void setEnrolledCourseId(UUID id) {
        this.enrolledCourseId = id;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<UUID> getEnrolledModules() {
        if (this.enrolledModules == null) {
            this.enrolledModules = new ArrayList<>();
        }
        return this.enrolledModules;
    }

    public void setEnrolledModules(ArrayList<UUID> enrolledModules) {
        this.enrolledModules = enrolledModules;
    }

    public void addEnrolledModule(UUID moduleId) {
        if (this.enrolledModules == null) {
            this.enrolledModules = new ArrayList<>();
        }

        this.enrolledModules.add(moduleId);
    }

    public void printEnrolledModules() {
        Course enrolledCourse = Courses.findCourse(this.enrolledCourseId);
        ArrayList<Module> courseModules = enrolledCourse.getModules();
        ArrayList<Module> enrolledModules = new ArrayList<>();
        for (UUID moduleID: this.getEnrolledModules()) {
            for (Module module: courseModules) {
                if (module.getId().equals(moduleID)) {
                    enrolledModules.add(module);
                }
            }
        }

        if (enrolledModules.size() == 0) {
            System.out.println("Oops there are no enrolled modules!");
            return;
        }

        AsciiTable moduleTable = new AsciiTable();
        moduleTable.addRule();
        moduleTable.addRow("Id", "Name", "Level", "Instructor");
        moduleTable.addRule();

        for (Module module: enrolledModules) {
            User moduleInstructor = Users.findInstructor(module.getInstructor());
            moduleTable.addRow(module.getId(), module.getName(), module.getLevel(), moduleInstructor.getUsername());
            moduleTable.addRule();
        }

        System.out.println(moduleTable.render());
    }

    public ArrayList<UUID> getCompletedModules() {
        return this.completedModules;
    }

    public void setCompletedModules(ArrayList<UUID> completedModules) {
        this.completedModules = completedModules;
    }
}
