package com.models.interfaces;

import com.loaders.CourseLoader;
import com.loaders.InstructorLoader;
import com.models.CompletedModuleWithGrade;
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
    ArrayList<Module> getEnrolledModulesWithDetails(CourseLoader courseLoader);
    void setEnrolledModules(ArrayList<UUID> enrolledModules);
    void addEnrolledModule(UUID id);
    void removeEnrolledModule(UUID moduleId);
    void printEnrolledModules(CourseLoader courseLoader, InstructorLoader instructorLoader);
    ArrayList<UUID> getCompletedModules();
    void addCompletedModule(UUID id);
    void setCompletedModules(ArrayList<UUID> completedModules);
    ArrayList<Grade> getGrades();
    void setGrades(ArrayList<Grade> grades);
    void addGrade(Grade grade);
    ArrayList<Module> getCompletedModulesWithDetails(CourseLoader courseLoader);
    ArrayList<CompletedModuleWithGrade> getCompletedModulesWithGrade(CourseLoader courseLoader);
    ArrayList<CompletedModuleWithGrade> getCompletedModulesWithGrade(CourseLoader courseLoader, int level);
}
