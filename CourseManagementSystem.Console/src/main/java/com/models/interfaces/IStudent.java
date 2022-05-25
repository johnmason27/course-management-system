package com.models.interfaces;

import com.loaders.CourseLoader;
import com.loaders.InstructorLoader;
import com.models.CompletedModuleWithGrade;
import com.models.Grade;
import com.models.Module;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Definition of a student model.
 */
public interface IStudent {
    /**
     * Get the id of the enrolled course.
     * @return The course id or null
     */
    UUID getEnrolledCourseId();

    /**
     * Set the id of the students enrolled course.
     * @param id Course id
     */
    void setEnrolledCourseId(UUID id);

    /**
     * Get the students level.
     * @return Level of the student
     */
    int getLevel();

    /**
     * Set the level of the student.
     * @param level Level to set too
     */
    void setLevel(int level);

    /**
     * Get the list of enrolled modules for the student.
     * @return Enrolled modules
     */
    ArrayList<UUID> getEnrolledModules();

    /**
     * Get the list of enrolled modules with the modules details.
     * @param courseLoader The course loader to load the course
     * @return The list of enrolled modules
     */
    ArrayList<Module> getEnrolledModulesWithDetails(CourseLoader courseLoader);

    /**
     * Set the enrolled modules for the student.
     * @param enrolledModules The new enrolled modules
     */
    void setEnrolledModules(ArrayList<UUID> enrolledModules);

    /**
     * Add an enrolled modules for the student.
     * @param id Id of the new module
     */
    void addEnrolledModule(UUID id);

    /**
     * Remove an enrolled module by its id.
     * @param moduleId Id of the module to remove
     */
    void removeEnrolledModule(UUID moduleId);

    /**
     * Print all enrolled modules for a student with the details of the instructor alongside
     * each module.
     * @param courseLoader Course loader to load the course and module details
     * @param instructorLoader Instructor loader to load the instructor for each module
     */
    void printEnrolledModules(CourseLoader courseLoader, InstructorLoader instructorLoader);

    /**
     * Get the list of completed module for the student.
     * @return The completed modules
     */
    ArrayList<UUID> getCompletedModules();

    /**
     * Add a new completed module onto the student.
     * @param id Id of the new completed module
     */
    void addCompletedModule(UUID id);

    /**
     * Set the list of completed modules for the student.
     * @param completedModules New list of completed modules
     */
    void setCompletedModules(ArrayList<UUID> completedModules);

    /**
     * Get all grades for a student.
     * @return All the students grades
     */
    ArrayList<Grade> getGrades();

    /**
     * Set all grades for a student.
     * @param grades The new grades
     */
    void setGrades(ArrayList<Grade> grades);

    /**
     * Add a grade onto a student.
     * @param grade The new Grade
     */
    void addGrade(Grade grade);

    /**
     * Get a list of completed modules for a student with all details.
     * @param courseLoader The course loader to load the course and module details
     * @return The list of modules
     */
    ArrayList<Module> getCompletedModulesWithDetails(CourseLoader courseLoader);

    /**
     * Get a list of completed modules with grade details for a student.
     * @param courseLoader The course loader to load the course and module details
     * @return The list of modules with associated grades
     */
    ArrayList<CompletedModuleWithGrade> getCompletedModulesWithGrade(CourseLoader courseLoader);

    /**
     * Get a list of completed modules with grade details for a certain level.
     * @param courseLoader The course loader to load the course and module details
     * @param level The level to get modules from
     * @return The list of modules with associated grades
     */
    ArrayList<CompletedModuleWithGrade> getCompletedModulesWithGrade(CourseLoader courseLoader, int level);
}
