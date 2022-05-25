package com.models;

/**
 * Wrapper object containing a module alongside it's corresponding grade.
 */
public class CompletedModuleWithGrade {
    private Module module;
    private Grade grade;

    /**
     * Initializes a CompletedModuleWithGrade.
     * @param module Completed module
     * @param grade Completed modules grade
     */
    public CompletedModuleWithGrade(Module module, Grade grade) {
        this.module = module;
        this.grade = grade;
    }

    /**
     * Get the completed module.
     * @return The completed module
     */
    public Module getModule() {
        return this.module;
    }

    /**
     * Set the completed module.
     * @param module The completed module
     */
    public void setModule(Module module) {
        this.module = module;
    }

    /**
     * Get the complete modules grade.
     * @return Completed modules grade
     */
    public Grade getGrade() {
        return this.grade;
    }

    /**
     * Set the completed modules grade.
     * @param grade Completed modules grade
     */
    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
