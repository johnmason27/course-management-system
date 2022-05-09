package com.models;

import java.util.ArrayList;
import java.util.UUID;

public class Instructor {
    private UUID id;
    private ArrayList<UUID> assignedModules;

    public Instructor(UUID id, ArrayList<UUID> assignedModules) {
        this.id = id;
        this.assignedModules = assignedModules;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ArrayList<UUID> getAssignedModules() {
        return this.assignedModules;
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
