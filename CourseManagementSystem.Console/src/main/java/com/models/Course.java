package com.models;

import de.vandermeer.asciitable.AsciiTable;

import java.util.ArrayList;
import java.util.UUID;

public class Course {
    private UUID id;
    private String name;
    private boolean availability;
    private ArrayList<Module> modules;

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
                .filter(m -> module.getName().equals(m.getName()))
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
        for (Module module : this.getModules()) {
            if (module.getId().equals(moduleId)) {
                module.setAvailability(availability);
                break;
            }
        }
    }

    public void printCourse(Course course) {
        AsciiTable coursesTable = new AsciiTable();

        coursesTable.addRule();
        coursesTable.addRow("Id", "Name", "Availability");
        coursesTable.addRule();
        coursesTable.addRow(course.getId(), course.getName(), course.getAvailability() ? "Available" : "Unavailable");
        coursesTable.addRule();

        System.out.println(coursesTable.render());
    }
}
