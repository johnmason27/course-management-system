package com.models;

import java.util.UUID;

public class Student {
    private UUID id;
    private UUID enrolledCourseId;

    public Student(UUID id, UUID enrolledCourseId) {
        this.id = id;
        this.enrolledCourseId = enrolledCourseId;
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
}
