package com.models;

public class CompletedModuleWithGrade {
    private Module module;
    private Grade grade;

    public CompletedModuleWithGrade(Module module, Grade grade) {
        this.module = module;
        this.grade = grade;
    }

    public Module getModule() {
        return this.module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Grade getGrade() {
        return this.grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
