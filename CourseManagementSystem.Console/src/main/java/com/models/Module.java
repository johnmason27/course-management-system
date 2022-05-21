package com.models;

import de.vandermeer.asciitable.AsciiTable;

import java.util.UUID;

public class Module {
    private UUID id;
    private String name;
    private boolean availability;
    private int level;
    private boolean optional;
    private UUID instructor;

    public Module(UUID id, String name, boolean availability, int level, boolean optional, UUID instructor) {
        this.id = id;
        this.name = name;
        this.availability = availability;
        this.level = level;
        this.optional = optional;
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
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("Id", "Name", "Level", "Availability", "Optional");
        table.addRule();
        table.addRow(module.getId(), module.getName(), module.getLevel(), module.getAvailability() ? "Available" : "Unavailable", module.getOptional() ? "Optional" : "Required");
        table.addRule();

        System.out.println(table.render());
    }

    public boolean getOptional() {
        return this.optional;
    }

    public void setOptional(boolean isOptional) {
        this.optional = isOptional;
    }

    public UUID getInstructor() {
        return this.instructor;
    }

    public void setInstructor(UUID instructor) {
        this.instructor = instructor;
    }
}
