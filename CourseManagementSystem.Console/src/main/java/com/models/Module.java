package com.models;

import de.vandermeer.asciitable.AsciiTable;

import java.util.UUID;

public class Module {
    private UUID id;
    private String name;
    private boolean availability;
    private int level;
    private UUID instructor;

    public Module(UUID id, String name, boolean availability, int level, UUID instructor) {
        this.id = id;
        this.name = name;
        this.availability = availability;
        this.level = level;
        this.instructor = instructor;
    }

    public UUID getId() {
        return this.id;
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

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void printModule(Module module) {
        AsciiTable modulesTable = new AsciiTable();

        modulesTable.addRule();
        modulesTable.addRow("Id", "Name", "Level", "Availability");
        modulesTable.addRule();
        modulesTable.addRow(module.getId(), module.getName(), module.getLevel(), module.getAvailability() ? "Available" : "Unavailable");
        modulesTable.addRule();

        System.out.println(modulesTable.render());
    }

    public UUID getInstructor() {
        return this.instructor;
    }

    public void setInstructor(UUID instructor) {
        this.instructor = instructor;
    }
}
