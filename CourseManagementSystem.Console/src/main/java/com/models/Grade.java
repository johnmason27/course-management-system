package com.models;

import com.models.interfaces.IGrade;

import java.util.UUID;

public class Grade implements IGrade {
    private UUID id;
    private UUID moduleId;
    private int grade;

    public Grade(UUID id, UUID moduleId, int grade) {
        this.id = id;
        this.moduleId = moduleId;
        this.grade = grade;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getModuleId() {
        return this.moduleId;
    }

    public void setModuleId(UUID moduleId) {
        this.moduleId = moduleId;
    }

    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
