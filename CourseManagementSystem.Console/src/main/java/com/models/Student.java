package com.models;

import java.util.UUID;

public class Student {
    private UUID id;
    private UUID enrolledCourseId;
    private int level;

    public Student(UUID id, UUID enrolledCourseId, int level) {
        this.id = id;
        this.enrolledCourseId = enrolledCourseId;
        this.level = level;
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
}
