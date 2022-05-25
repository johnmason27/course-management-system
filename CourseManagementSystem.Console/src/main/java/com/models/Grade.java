package com.models;

import java.util.UUID;

/**
 * Grade given to students for completing modules.
 */
public class Grade {
    private UUID id;
    private UUID moduleId;
    private int grade;

    /**
     * Initialize a Grade.
     * @param id Grade id
     * @param moduleId Corresponding module id
     * @param grade Grade (percentage as integer)
     */
    public Grade(UUID id, UUID moduleId, int grade) {
        this.id = id;
        this.moduleId = moduleId;
        this.grade = grade;
    }

    /**
     * Get the grade id.
     * @return Grade id
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the grade id.
     * @param id Grade id
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Get the corresponding module id.
     * @return Module id
     */
    public UUID getModuleId() {
        return this.moduleId;
    }

    /**
     * Set the corresponding module id.
     * @param moduleId Module id
     */
    public void setModuleId(UUID moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * Get the grade (percentage as integer).
     * @return Grade (percentage as integer)
     */
    public int getGrade() {
        return this.grade;
    }

    /**
     * Set the grade (percentage as integer).
     * @param grade Grade (percentage as integer)
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }
}
