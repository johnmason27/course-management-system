package com.models;

import com.models.interfaces.ICourse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Course made up of modules that students can take.
 */
public class Course implements ICourse {
    private UUID id;
    private String name;
    private boolean availability;
    private ArrayList<Module> modules;

    /**
     * Initialize a Course.
     * @param id Course id
     * @param name Course name
     * @param availability Course availability
     * @param modules Course modules
     */
    public Course(UUID id, String name, boolean availability, ArrayList<Module> modules) {
        this.id = id;
        this.name = name;
        this.availability = availability;
        this.modules = modules;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getAvailability() {
        return this.availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public ArrayList<Module> getModules() {
        return this.modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    public void addModule(Module module) {
        this.modules.add(module);
    }

    public void removeModule(Module module) {
        this.modules.remove(module);
    }

    public Module findModule(Module module) {
        return this.modules.stream()
                .filter(m -> m.getName().equals(module.getName()))
                .findAny()
                .orElse(null);
    }

    public void updateModule(Module module) {
        int index = 0;
        UUID moduleId = module.getId();

        for (int i = 0; i < this.modules.size(); i ++) {
            if (this.modules.get(i).getId().equals(moduleId)) {
                index = i;
                break;
            }
        }

        this.modules.set(index, module);
    }

    public void toggleModuleAvailability(UUID moduleId, boolean availability) {
        for (Module m : this.getModules()) {
            if (m.getId().equals(moduleId)) {
                m.setAvailability(availability);
                break;
            }
        }
    }

    public ArrayList<Module> getUnassignedModules() {
        List<Module> availableModules = this.modules.stream()
                .filter(m -> m.getAvailability() && m.getInstructor() == null)
                .toList();

        return new ArrayList<>(availableModules);
    }

    public ArrayList<Module> getAssignedModules() {
        List<Module> availableModules = this.modules.stream()
                .filter(m -> m.getAvailability() && m.getInstructor() != null)
                .toList();

        return new ArrayList<>(availableModules);
    }
}
