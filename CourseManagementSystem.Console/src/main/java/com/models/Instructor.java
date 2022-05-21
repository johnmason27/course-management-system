package com.models;

import com.loaders.CourseLoader;
import com.models.interfaces.IInstructor;

import java.util.ArrayList;
import java.util.UUID;

public class Instructor extends User implements IInstructor {
    private ArrayList<UUID> assignedModules;

    public Instructor(UUID id,  UserType userType, String forename, String surname, String email, String username, String password, ArrayList<UUID> assignedModules) {
        super(id, userType, forename, surname, email, username, password);
        this.assignedModules = assignedModules;
    }

    public ArrayList<UUID> getAssignedModules() {
        return this.assignedModules;
    }

    public ArrayList<Module> getAssignedModulesWithDetails(CourseLoader courseLoader) {
        ArrayList<Module> assignedModules = new ArrayList<>();

        for (Course c : courseLoader.loadAll()) {
            ArrayList<Module> courseModules = c.getModules();
            for (Module m : courseModules) {
                if (m.getInstructor() != null && m.getInstructor().equals(this.getId())) {
                    assignedModules.add(m);
                }
            }
        }

        return assignedModules;
    }

    public void setAssignedModules(ArrayList<UUID> assignedModules) {
        this.assignedModules = assignedModules;
    }

    public void addAssignedModule(UUID moduleId) {
        if (this.assignedModules.size() == 4) {
            throw new UnsupportedOperationException("Cannot assign more than 4 modules to an instructor at one time.");
        } else {
            this.assignedModules.add(moduleId);
        }
    }

    public void removeAssignedModule(UUID moduleId) {
        this.assignedModules.remove(moduleId);
    }
}
