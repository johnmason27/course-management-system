package com.models.interfaces;

import com.models.Module;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Definition of a course model.
 */
public interface ICourse {
    /**
     * Get the id of the course.
     * @return Course id
     */
    UUID getId();

    /**
     * Set the id of the course.
     * @param id Id to set to the course too
     */
    void setId(UUID id);

    /**
     * Get the name of the course.
     * @return Course name
     */
    String getName();

    /**
     * Set the name of the course.
     * @param name Name to set the course too
     */
    void setName(String name);

    /**
     * Get the availability of the course.
     * @return The availability
     */
    boolean getAvailability();

    /**
     * Set the availability of the course.
     * @param availability The availability to set
     */
    void setAvailability(boolean availability);

    /**
     * Get the list of modules associated with the course.
     * @return The course modules
     */
    ArrayList<Module> getModules();

    /**
     * Set the list of modules associated with the course.
     * @param modules The course modules to set
     */
    void setModules(ArrayList<Module> modules);

    /**
     * Add a module to the course modules.
     * @param module Module to add
     */
    void addModule(Module module);

    /**
     * Remove a given module from the course.
     * @param module Module to remove
     */
    void removeModule(Module module);

    /**
     * Find a module on a course.
     * @param module The module to find
     * @return The module found or null
     */
    Module findModule(Module module);

    /**
     * Update a module on a course.
     * @param module The module to update
     */
    void updateModule(Module module);

    /**
     * Toggle the availability of a given module.
     * @param moduleId The module id
     * @param availability The availability to set
     */
    void toggleModuleAvailability(UUID moduleId, boolean availability);

    /**
     * Get all unassigned modules on a course.
     * @return All unassigned modules
     */
    ArrayList<Module> getUnassignedModules();

    /**
     * Get all assigned modules on a course.
     * @return All assigned modules
     */
    ArrayList<Module> getAssignedModules();
}
