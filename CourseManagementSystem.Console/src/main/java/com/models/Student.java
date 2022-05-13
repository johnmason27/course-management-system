package com.models;

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
        
    }

    public ArrayList<UUID> getCompletedModules() {
        return this.completedModules;
    }

    public void setCompletedModules(ArrayList<UUID> completedModules) {
        this.completedModules = completedModules;
    }
}
