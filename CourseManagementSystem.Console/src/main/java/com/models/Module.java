package com.models;

import de.vandermeer.asciitable.AsciiTable;

import java.util.UUID;

/**
 * Module for students to take and instructors to teach.
 */
public class Module {
    private UUID id;
    private String name;
    private boolean availability;
    private int level;
    private boolean optional;
    private UUID instructor;

    /**
     * Initialize a Module.
     * @param id Module id
     * @param name Module name
     * @param availability Module availability
     * @param level Module level
     * @param optional Is the module optional?
     * @param instructor Instructor of the module
     */
    public Module(UUID id, String name, boolean availability, int level, boolean optional, UUID instructor) {
        this.id = id;
        this.name = name;
        this.availability = availability;
        this.level = level;
        this.optional = optional;
        this.instructor = instructor;
    }

    /**
     * Get the id of the module.
     * @return Module id
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id of the module.
     * @param id Module id
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Get the name of the module.
     * @return Module name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name of the module.
     * @param name Module name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the availability of the module.
     * @return Module availability
     */
    public boolean getAvailability() {
        return this.availability;
    }

    /**
     * Set the availability of the module.
     * @param availability Module availability
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    /**
     * Get the level of the module.
     * @return Module level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Set the level of the module.
     * @param level Module level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Print the module in an ASCII table.
     */
    public void printModule() {
        AsciiTable table = new AsciiTable();

        table.addRule();
        table.addRow("Id", "Name", "Level", "Availability", "Optional");
        table.addRule();
        table.addRow(this.getId(), this.getName(), this.getLevel(), this.getAvailability() ? "Available" : "Unavailable", this.getOptional() ? "Optional" : "Required");
        table.addRule();

        System.out.println(table.render());
    }

    /**
     * Get the optionality of the module.
     * @return Module optionality
     */
    public boolean getOptional() {
        return this.optional;
    }

    /**
     * Set the optionality of the module.
     * @param isOptional Whether the module is optional or not
     */
    public void setOptional(boolean isOptional) {
        this.optional = isOptional;
    }

    /**
     * Get the id of instructor teaching the module.
     * @return Instructor id
     */
    public UUID getInstructor() {
        return this.instructor;
    }

    /**
     * Set the id of instructor teaching the module.
     * @param instructor Instructor id
     */
    public void setInstructor(UUID instructor) {
        this.instructor = instructor;
    }
}
