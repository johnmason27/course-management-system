package com.models.interfaces;

import java.util.ArrayList;
import java.util.UUID;

public interface IInstructor {
    ArrayList<UUID> getAssignedModules();
    void setAssignedModules(ArrayList<UUID> assignedModules);
    void addAssignedModule(UUID id);
    void removeAssignedModule(UUID id);
}
