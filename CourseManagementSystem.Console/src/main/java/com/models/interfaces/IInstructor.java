package com.models.interfaces;

import com.loaders.CourseLoader;
import com.models.Module;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Definition of an instructor model.
 */
public interface IInstructor {
    /**
     * Get all assigned modules to an instructor.
     * @return All assigned modules
     */
    ArrayList<UUID> getAssignedModules();

    /**
     * Set all assigned modules to an instructor.
     * @param assignedModules All assigned modules
     */
    void setAssignedModules(ArrayList<UUID> assignedModules);

    /**
     * Add an assigned module to an instructor.
     * @param id The id of the module
     */
    void addAssignedModule(UUID id);

    /**
     * Remove a given module from the assigned modules to an instructor.
     * @param id The id of the module to remove
     */
    void removeAssignedModule(UUID id);

    /**
     * Get the assigned modules with full module details.
     * @param courseLoader The course loader to load the course with details
     * @return The assigned modules with their details
     */
    ArrayList<Module> getAssignedModulesWithDetails(CourseLoader courseLoader);
}
