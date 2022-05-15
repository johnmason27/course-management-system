package com.models.interfaces;

import com.models.Grade;
import com.models.Module;

import java.util.ArrayList;
import java.util.UUID;

public interface IStudent {
    UUID getEnrolledCourseId();
    void setEnrolledCourseId(UUID id);
    int getLevel();
    void setLevel(int level);
    ArrayList<UUID> getEnrolledModules();
    ArrayList<Module> getEnrolledModulesWithDetails();
    void setEnrolledModules(ArrayList<UUID> enrolledModules);
    void addEnrolledModule(UUID id);
    void printEnrolledModules();
    ArrayList<UUID> getCompletedModules();
    void setCompletedModules(ArrayList<UUID> completedModules);
    ArrayList<Grade> getGrades();
    void setGrades(ArrayList<Grade> grades);
    void addGrade(Grade grade);
}
