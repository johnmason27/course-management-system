package com.models.interfaces;

import com.models.Module;

import java.util.ArrayList;
import java.util.UUID;

public interface ICourse {
    UUID getId();
    void setId(UUID id);
    String getName();
    void setName(String name);
    boolean getAvailability();
    void setAvailability(boolean availability);
    ArrayList<Module> getModules();
    void setModules(ArrayList<Module> modules);
    void addModule(Module module);
    void removeModule(Module module);
    Module findModule(Module module);
    void updateModule(Module module);
    void toggleModuleAvailability(UUID moduleId, boolean availability);
    ArrayList<Module> getUnassignedModules();
    ArrayList<Module> getAssignedModules();
}
